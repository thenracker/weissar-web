# Doména + hosting – návod krok za krokem

Ceny jsou orientační (stav podzim 2026). Před nákupem si je vždycky ověř u registrátora.

---

## TL;DR

| Co | Doporučení | Cena |
|---|---|---|
| Doména | **weissar.app** (+ volitelně **weissar.cz** jako přesměrování) | ~400 Kč/rok za .app, ~250 Kč/rok za .cz |
| Hosting | **Google Cloud Run** (běží tam Ktor v Dockeru) + **Firebase Hosting** (doména + HTTPS + CDN) | **0 Kč** při běžné návštěvnosti osobního webu |
| **Celkem** | | **cca 35–55 Kč měsíčně** (jde jen o doménu rozpočítanou na měsíce) |

---

## 1. Výběr domény

Dostupnost jsem ověřil 30. 10. 2026. V tu chvíli byly **volné všechny tyhle**:

| Doména | Pro | Proti |
|---|---|---|
| **weissar.app** ⭐ | sedí k `svatebka.app` a `univerzita.app`, krátká, HTTPS je povinné (bezpečnější) | o něco dražší |
| weissar.cz | levná, česká, důvěryhodná pro české klienty | web je anglicky |
| weissar.dev | „vývojářská“ | méně známá koncovka |
| petrweissar.app | celé jméno | delší |
| weissar.com | univerzální | zbytečné, když máš .app |

**Doporučuju:** koupit **weissar.app** jako hlavní doménu. Pokud chceš, kup i **weissar.cz** (za pár stovek ročně) a přesměruj ji na .app. Nikdo ti ji pak nezabere.

## 2. Nákup domény

### weissar.app
- **Nejjednodušší:** kup ji u stejného registrátora, kde máš `svatebka.app`. Všechno pak budeš mít na jednom místě.
- Jinak doporučuju **Cloudflare Registrar** (prodává za nákupní cenu, bez přirážek) nebo **Porkbun**.
- Postup: registrace účtu → vyhledat `weissar.app` → koupit na 1 rok → **zapnout automatické prodloužení** (auto-renew).
- Pozor: Google Domains už neexistuje (přešlo pod Squarespace).

### weissar.cz (volitelně)
- České registrátory: **Wedos**, **Forpsi**, **Active24**, **Subreg**.
- První rok bývá v akci levnější a prodloužení pak stojí cca 250–350 Kč/rok.
- Při registraci .cz se vyplňuje „kontakt držitele“ (jméno, adresa). To je normální.

## 3. Hosting – proč Cloud Run + Firebase Hosting

Ktor je server, potřebuje tedy místo, kde poběží JVM. Doporučená varianta:

- **Cloud Run** spustí `Dockerfile` z repa. Když na web nikdo nechodí, uspí se a nic nestojí. Free tier (2 miliony requestů měsíčně) osobní web nikdy nevyčerpá.
- **Firebase Hosting** stojí před Cloud Run. Řeší vlastní doménu, HTTPS certifikát (zdarma, automaticky) a CDN. Firebase znáš ze Svatebky, takže konzole ti bude povědomá.
- Konfigurace je v repu připravená v `firebase.json`.

Nevýhoda: první návštěva po delší pauze trvá o 1–3 sekundy déle, protože se kontejner probouzí (cold start). Statické soubory se ale cachují v CDN, takže v praxi to skoro nepoznáš.

## 4. Nasazení – krok za krokem

### 4.1 Jednorázová příprava
1. Nainstaluj nástroje:
   ```bash
   brew install --cask google-cloud-sdk
   npm install -g firebase-tools
   ```
2. Přihlas se:
   ```bash
   gcloud auth login
   firebase login
   ```
3. V [Firebase konzoli](https://console.firebase.google.com) vytvoř projekt, třeba `weissar-web`.
4. Přepni projekt na tarif **Blaze** (pay-as-you-go). Rewrite na Cloud Run bez něj nefunguje. Platí se ale jen to, co přesáhne free limity, tedy u tebe nic.
5. **Hned si nastav rozpočtový alert:** Google Cloud Console → Billing → Budgets & alerts → rozpočet třeba 50 Kč s upozorněním na e-mail. Kdyby se cokoli pokazilo, hned o tom víš.

### 4.2 Nasazení serveru na Cloud Run
Z kořene repa:
```bash
gcloud config set project <ID-tveho-projektu>
gcloud run deploy weissar-web \
  --source . \
  --region europe-west1 \
  --allow-unauthenticated \
  --memory 512Mi
```
- Google si sám zbuilduje Docker image podle `Dockerfile` a spustí ho.
- Na konci vypíše URL (`https://weissar-web-xxxx.europe-west1.run.app`). Otevři ji a zkontroluj web.
- Při prvním spuštění se zeptá na povolení API. Na všechno odpověz `y`.

### 4.3 Firebase Hosting před Cloud Run
```bash
firebase use --add          # vyber projekt z kroku 4.1
firebase deploy --only hosting
```
Web pak běží i na `https://<projekt>.web.app`.

### 4.4 Připojení domény
1. Firebase konzole → **Hosting** → **Add custom domain** → zadej `weissar.app`.
2. Firebase ti ukáže **DNS záznamy** (typicky `A` a `TXT`).
3. Přihlas se u registrátora domény → **DNS nastavení** → přidej přesně ty záznamy.
4. Počkej. Obvykle to trvá pár minut, výjimečně až 24 hodin. Firebase pak sám vystaví HTTPS certifikát.
5. Totéž zopakuj pro `www.weissar.app` (Firebase nabídne přesměrování na hlavní doménu).
6. (Volitelně) `weissar.cz`: u českého registrátora nastav přesměrování (URL redirect) na `https://weissar.app`, nebo ji přidej ve Firebase jako další doménu s přesměrováním.

### 4.5 Každá další aktualizace webu
```bash
gcloud run deploy weissar-web --source . --region europe-west1
```
Stačí jen tohle. Firebase Hosting není potřeba znovu nasazovat, pokud neměníš `firebase.json`.

> Další krok do budoucna: GitHub Action, která tohle udělá automaticky po každém pushi do `main`.

## 5. ⚠️ Pozor na GitHub Pages a stránku Univerzity

Stará verze webu teď běží z větve `main` přes GitHub Pages. Je tam i
`university-delete-account-request.html`, na kterou nejspíš odkazuje Google Play (stránka pro smazání účtu).

- **Po mergi `dev` → `main` GitHub Pages přestane fungovat**, protože v kořeni repa už nebude `index.html`.
- Postup:
  1. Nejdřív nasaď nový web na Cloud Run a připoj doménu (kroky 4.x).
  2. Ověř, že funguje `https://weissar.app/university-delete-account-request.html`.
  3. Změň URL v **Google Play Console** (Univerzita → Data safety / Delete account URL) na novou adresu.
  4. Teprve potom mergni `dev` do `main` a v nastavení repa vypni GitHub Pages.

## 6. Měsíční náklady – shrnutí

| Položka | Ročně | Měsíčně |
|---|---|---|
| weissar.app | ~350–450 Kč | ~30–40 Kč |
| weissar.cz (volitelně) | ~250–350 Kč | ~20–30 Kč |
| Cloud Run | 0 Kč (free tier) | 0 Kč |
| Firebase Hosting | 0 Kč (free limity) | 0 Kč |
| Artifact Registry (Docker image) | ~0–5 Kč | ~0 Kč |
| **Celkem** | **~350–800 Kč** | **~30–70 Kč** |

### Alternativa: vlastní VPS
Pokud bys chtěl mít server „pořád vzhůru“ a spouštět si na něm i další věci, existuje i varianta s VPS (např. Hetzner Cloud, nejmenší stroj zhruba 4–6 € měsíčně):
Ubuntu + Docker + [Caddy](https://caddyserver.com) (automatické HTTPS). Tady se ale o server staráš sám (aktualizace, bezpečnost). Pro začátek bych zůstal u Cloud Run.
