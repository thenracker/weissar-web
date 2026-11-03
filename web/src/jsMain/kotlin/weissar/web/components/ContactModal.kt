package weissar.web.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import kotlinx.browser.window
import org.jetbrains.compose.web.attributes.InputType
import org.jetbrains.compose.web.attributes.disabled
import org.jetbrains.compose.web.attributes.placeholder
import org.jetbrains.compose.web.attributes.required
import org.jetbrains.compose.web.dom.Button
import org.jetbrains.compose.web.dom.Form
import org.jetbrains.compose.web.dom.H3
import org.jetbrains.compose.web.dom.Input
import org.jetbrains.compose.web.dom.Label
import org.jetbrains.compose.web.dom.P
import org.jetbrains.compose.web.dom.Span
import org.jetbrains.compose.web.dom.Text
import org.jetbrains.compose.web.dom.TextArea
import org.w3c.fetch.RequestInit
import weissar.web.i18n.tr
import weissar.web.theme.AppStyles
import kotlin.js.json

/**
 * Web3Forms access key – messages are delivered to the e-mail it was created for, so the address isn't in the page.
 * The key is public by design, it only allows sending to that one address.
 */
private const val WEB3FORMS_ACCESS_KEY = "cb21986e-161f-47d0-8a28-918d475587ee"
private const val WEB3FORMS_URL = "https://api.web3forms.com/submit"

private enum class SendState { Idle, Sending, Sent, Failed }

/** Message form, the optional e-mail becomes Reply-To of the delivered mail. */
@Composable
fun ContactModal(onClose: () -> Unit) {
    var message by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var state by remember { mutableStateOf(SendState.Idle) }

    val title = tr("Napište mi", "Get in touch")

    Modal(label = title, onClose = onClose) {
        H3({ classes(AppStyles.modalTitle) }) { Text(title) }

        if (state == SendState.Sent) {
            P({ classes(AppStyles.modalText) }) {
                Text(
                    if (email.isBlank()) {
                        tr("Díky, zpráva dorazila.", "Thanks, the message got through.")
                    } else {
                        tr("Díky, zpráva dorazila. Ozvu se vám na $email.", "Thanks, the message got through. I'll get back to you at $email.")
                    },
                )
            }
            Button({
                classes(AppStyles.button, AppStyles.modalLink, AppStyles.formSubmit)
                onClick { onClose() }
            }) { Span({ classes(AppStyles.cardText) }) { Text(tr("Zavřít", "Close")) } }
            return@Modal
        }

        Form(attrs = {
            classes(AppStyles.form)
            addEventListener("submit") {
                it.preventDefault()
                state = SendState.Sending
                send(message, email) { success -> state = if (success) SendState.Sent else SendState.Failed }
            }
        }) {
            Label(forId = "contact-message", attrs = { classes(AppStyles.formLabel) }) { Text(tr("Zpráva", "Message")) }
            TextArea(value = message, attrs = {
                id("contact-message")
                classes(AppStyles.formInput, AppStyles.formTextArea)
                required()
                placeholder(tr("S čím vám můžu pomoct?", "How can I help you?"))
                onInput { message = it.value }
            })

            Label(forId = "contact-email", attrs = { classes(AppStyles.formLabel) }) {
                Text(tr("Váš e-mail (nepovinné)", "Your e-mail (optional)"))
            }
            Input(InputType.Email, attrs = {
                id("contact-email")
                classes(AppStyles.formInput)
                attr("autocomplete", "email")
                placeholder(tr("Ať vám můžu odpovědět", "So I can reply"))
                value(email)
                onInput { email = it.value }
            })

            if (state == SendState.Failed) {
                P({ classes(AppStyles.formError) }) {
                    Text(tr("Zprávu se nepodařilo odeslat, zkuste to prosím znovu.", "The message couldn't be sent, please try again."))
                }
            }

            Button(attrs = {
                classes(AppStyles.button, AppStyles.modalLink, AppStyles.formSubmit)
                attr("type", "submit")
                if (state == SendState.Sending || message.isBlank()) disabled()
            }) {
                Span({ classes(AppStyles.cardText) }) {
                    Text(if (state == SendState.Sending) tr("Odesílám…", "Sending…") else tr("Odeslat", "Send"))
                }
                Icon(src = "arrow-more.svg")
            }
        }
    }
}

/** Web3Forms uses the `email` field as Reply-To. */
private fun send(message: String, email: String, onResult: (Boolean) -> Unit) {
    val body = json(
        "access_key" to WEB3FORMS_ACCESS_KEY,
        "subject" to "Zpráva z weissar.dev",
        "from_name" to "weissar.dev",
        "message" to message.trim(),
    )
    if (email.isNotBlank()) body["email"] = email.trim()

    window.fetch(
        WEB3FORMS_URL,
        RequestInit(
            method = "POST",
            headers = json("Content-Type" to "application/json", "Accept" to "application/json"),
            body = JSON.stringify(body),
        ),
    )
        .then { response -> response.json() }
        .then { result -> onResult(result.asDynamic().success == true) }
        .catch { onResult(false) }
}
