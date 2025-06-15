## Authetication - Login Flow

### **Diagram:**

[![](https://mermaid.ink/img/pako:eNrNVNtu00AQ_ZXRPjXCDU2CE2clKtEWENeipggJ-WVjT5xV7F2zl7ah6r8zvqUFp9BH_GR7z9kz5-zs3LJEp8g4s_jDo0rwTIrMiCJWQE8pjJOJLIVy8NWi6f99Y7RyqNL-yolINnsXPuA2ybXY9FfOhBNLYTFWzVoleXh83GlweJUkaC1aEGUJQqWQ68yCbOEdjiidBoeLypd1RPFujYq0hJO6ZXSww99ULtB5oyy8_3YJTpOJuC_QuuPw9vUlPC8QDq6lWzfwQYNuIQTufHH4qPUGfAnLLWxa7Xdpt73Im5Qh1WTx8_kl4I20jvwBlQ7pLh1on05hb_Wdt5oHz0CrEy1MeqqLMkeH6cuVyO-j_sNeVQaHxVpfWyIuK6JUGdjEICo4wGE2DGAl87wqLpcJKouD-516B7fwy0LSIbRQYq30XuFdrl_OFxTsA-0mYF_nQ44G_RgeBr0QV9g5t-j67p3xT03S-rrtwDrhvH0ksX5DiExI9Y8yH-mHJ5W18hT_Lg842Gtx8JfjPZO2zMXWQkGFVlcqby9He84NFalLmrYUuUGRbpuutPX9kxaSVu8_q7qaPCxgmZEp49WeASvQEIc-2W0FihndqgJjxuk1FWYTs1jdEYdm0Xeti45mtM_WjNfXJWC-pLq7KbmDkByaU-2VYzyqd2D8lt0wPhlNh2EYHU3no-lsPh5HLwK2ZXw2Ho7CyegonIfhfDoZR3cB-1lrHg2jcDaLZqPJeDydjKLJNGCYSqfNp2ZQ1_P67hfOSeNa?type=png)](https://mermaid.live/edit#pako:eNrNVNtu00AQ_ZXRPjXCDU2CE2clKtEWENeipggJ-WVjT5xV7F2zl7ah6r8zvqUFp9BH_GR7z9kz5-zs3LJEp8g4s_jDo0rwTIrMiCJWQE8pjJOJLIVy8NWi6f99Y7RyqNL-yolINnsXPuA2ybXY9FfOhBNLYTFWzVoleXh83GlweJUkaC1aEGUJQqWQ68yCbOEdjiidBoeLypd1RPFujYq0hJO6ZXSww99ULtB5oyy8_3YJTpOJuC_QuuPw9vUlPC8QDq6lWzfwQYNuIQTufHH4qPUGfAnLLWxa7Xdpt73Im5Qh1WTx8_kl4I20jvwBlQ7pLh1on05hb_Wdt5oHz0CrEy1MeqqLMkeH6cuVyO-j_sNeVQaHxVpfWyIuK6JUGdjEICo4wGE2DGAl87wqLpcJKouD-516B7fwy0LSIbRQYq30XuFdrl_OFxTsA-0mYF_nQ44G_RgeBr0QV9g5t-j67p3xT03S-rrtwDrhvH0ksX5DiExI9Y8yH-mHJ5W18hT_Lg842Gtx8JfjPZO2zMXWQkGFVlcqby9He84NFalLmrYUuUGRbpuutPX9kxaSVu8_q7qaPCxgmZEp49WeASvQEIc-2W0FihndqgJjxuk1FWYTs1jdEYdm0Xeti45mtM_WjNfXJWC-pLq7KbmDkByaU-2VYzyqd2D8lt0wPhlNh2EYHU3no-lsPh5HLwK2ZXw2Ho7CyegonIfhfDoZR3cB-1lrHg2jcDaLZqPJeDydjKLJNGCYSqfNp2ZQ1_P67hfOSeNa)


### Edit on [Mermaid](https://mermaid.live/edit)  editor:
```
sequenceDiagram
    participant User
    participant Frontend
    participant Backend
    participant Keycloak
    participant Database

    User->>Frontend: Accesses app and logs in
    Frontend->>Keycloak: Requests authentication
    Keycloak-->>Frontend: Returns JWT token

    Frontend->>Backend: GET /me (with token)
    Backend->>Database: Look up by keycloakId

    alt User does NOT exist in the database
        Backend-->>Frontend: Returns Keycloak data + onBoardCompleted=false

        Frontend->>User: Shows onboarding screen (e.g., fill in license)
        User->>Frontend: Submits license info
        Frontend->>Backend: POST /onboarding (with user data)
        Backend->>Database: Save data + set onBoardCompleted=true
        Backend-->>Frontend: Returns success status

        Frontend->>Backend: GET /me (again)
        Backend->>Database: Look up by keycloakId
        Backend-->>Frontend: Returns full user data (onBoardCompleted=true)
        Frontend->>User: Displays main application screen
    else User already exists and is complete
        Backend-->>Frontend: Returns full user data (onBoardCompleted=true)
        Frontend->>User: Displays main application screen
    end

```