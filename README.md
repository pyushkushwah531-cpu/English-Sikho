# English सीखो Pro (100% Native Android + Vivo & Indus Appstore Ready)

हिंदी माध्यम के छात्रों के लिए बनाया गया **₹0 (बिल्कुल फ्री)** 100% Native Android ऐप (Kotlin + Jetpack Compose + Material 3)।

## इसमें शामिल Pro फीचर्स
1. **2-मिनट लेवल टेस्ट:** Zero/Basic, Intermediate, या Job-Ready स्तर की पहचान।
2. **Daily 10-Minute Plan + Streak + XP:** रोज़ का छोटा लक्ष्य और डिजिटल सर्टिफिकेट।
3. **AI Roleplay & Pronunciation Studio:** Job Interview, Doctor, Shop बातचीत + 0.75x/1.0x ऑडियो + Android माइक (Speech-to-Text) उच्चारण जाँच।
4. **सोचो हिंदी में, बोलो English में (Bridge):** शब्द-दर-शब्द अनुवाद के जाल और Hinglish-to-3-Level English कन्वर्टर।
5. **Visual Grammar:** बिना रटे Tenses टाइमलाइन, Prepositions (`in/on/at`), और Modals।
6. **SRS Flashcards & Confusing Words:** *Accept/Except*, *Principal/Principle* जैसे शब्द और फ्लैशकार्ड।
7. **Tap-to-Translate कहानियाँ:** किसी भी कठिन शब्द पर टैप करते ही तुरंत हिंदी अर्थ।
8. **Writing & Email Coach:** छुट्टी की अर्ज़ी, जॉब ईमेल और आम गलतियों का तुरंत सुधार।
9. **Native Home Screen Widget:** फोन की होम स्क्रीन पर "Word of the Day" और Streak।

## GitHub Actions से Debug और Store Release APK कैसे बनाएं
1. इस पूरे प्रोजेक्ट को अपनी **Public GitHub Repository** में अपलोड करें (ध्यान रखें `.github/workflows/build-apk.yml` फ़ोल्डर भी साथ अपलोड हो)।
2. GitHub में ऊपर **Actions** टैब पर क्लिक करें और **"Build English Seekho Native APK"** चलाएं।
3. 4–5 मिनट में नीचे **Artifacts** से दो APK मिलेंगे:
   - `EnglishSeekhoPro-Debug-APK` ➔ तुरंत अपने फोन में टेस्ट करने के लिए
   - `EnglishSeekhoPro-Store-Release-APK` ➔ **Vivo App Store** और **Indus Appstore** पर पब्लिश करने के लिए!

## स्थायी App Updates के लिए Keystore Secret (वैकल्पिक लेकिन Store अपडेट के लिए उपयोगी)
अगर आप भविष्य में Vivo App Store या Indus Appstore पर Version 2 डालना चाहते हैं, तो Repository Settings ➔ Secrets and variables ➔ Actions में ये 4 Secrets जोड़ सकते हैं:
- `KEYSTORE_BASE64` (आपकी `.jks` फाइल का base64)
- `KEYSTORE_PASSWORD`
- `KEY_ALIAS`
- `KEY_PASSWORD`
