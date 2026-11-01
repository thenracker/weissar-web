# Doména a hosting

## Jak to teď běží

| Co | Kde |
|---|---|
| Web | **https://weissar.dev** (GitHub Pages, zdarma) |
| Doména | **weissar.dev**, koupená v **Cloudflare Registrar** (auto-renew zapnutý) |
| DNS | Cloudflare (záznamy jen jako „DNS only“, šedý mráček) |
| HTTPS | certifikát vystavuje GitHub Pages sám, HTTPS je vynucené |
| Deploy | GitHub Action `.github/workflows/pages.yml` po každém pushi do `dev` |

Web je čistě statický (Compose HTML → `web.js` + soubory z `resources`), takže server ani Cloud Run nepotřebuje.
Ktor server v `:server` slouží jen pro lokální vývoj.

Staré adresy `https://thenracker.github.io/weissar-web/...` GitHub sám přesměruje na `https://weissar.dev/...`,
takže odkaz na `university-delete-account-request.html` v Google Play dál funguje. Časem ho tam ale můžeš přepsat
na `https://weissar.dev/university-delete-account-request.html`.

## DNS záznamy weissar.dev (Cloudflare)

| Type | Name | Content |
|---|---|---|
| A | `@` | `185.199.108.153`, `185.199.109.153`, `185.199.110.153`, `185.199.111.153` (4 záznamy) |
| AAAA | `@` | `2606:50c0:8000::153`, `2606:50c0:8001::153`, `2606:50c0:8002::153`, `2606:50c0:8003::153` (4 záznamy) |
| CNAME | `www` | `thenracker.github.io` |
| TXT | `_github-pages-challenge-thenracker` | ověřovací kód z GitHubu (Settings → Pages → Verified domains) |

Vlastní doména je nastavená v repu (Settings → Pages → Custom domain). U deploye přes Actions se soubor `CNAME` nepoužívá.
Po změně domény je potřeba spustit nový deploy (`gh workflow run pages.yml --ref dev`), jinak web vrací 404.

## Pozor na .dev

Celá koncovka .dev funguje **jen přes HTTPS** (je v HSTS preload listu prohlížečů). Kdyby se certifikát nevystavil,
web se vůbec neotevře – nejčastější příčina je oranžový mráček (proxy) u záznamů v Cloudflare.

## Až budeš chtít weissar.cz

1. Koupit u českého registrátora (např. Wedos, Forpsi), zapnout automatické prodloužení.
2. V Cloudflare **Add a domain** → `weissar.cz` → plán Free a nameservery, které Cloudflare ukáže, zadat u registrátora.
3. V Cloudflare u weissar.cz přidat `A @ 192.0.2.1` a `CNAME www weissar.cz`, oba **proxied** (oranžový mráček).
   Jde jen o zástupné záznamy, aby přes ně šel provoz do Cloudflare.
4. **Rules → Redirect Rules**: vše z `weissar.cz` a `www.weissar.cz` → `https://weissar.dev${path}`, kód 301.
   HTTPS certifikát pro .cz vystaví Cloudflare sám.

## Náklady

| Položka | Ročně |
|---|---|
| weissar.dev | ~300 Kč |
| weissar.cz (až bude) | ~200–300 Kč |
| GitHub Pages, Cloudflare DNS | 0 Kč |
