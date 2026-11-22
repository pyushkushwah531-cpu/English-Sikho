package com.englishseekho.pro.data

object CurriculumRepository {

    val levelTestQuestions = listOf(
        LevelTestQuestion(
            id = 1,
            promptHi = "सही वाक्य चुनें (मुझे भूख लगी है):",
            sentenceEn = "___ hungry right now.",
            options = listOf("I have", "I am", "I do", "Me is"),
            correctIndex = 1,
            explanationHi = "भूख/प्यास/खुशी महसूस करने के लिए 'I am' लगता है, 'I have' नहीं।"
        ),
        LevelTestQuestion(
            id = 2,
            promptHi = "कल मैं स्कूल गया था — सही शब्द भरें:",
            sentenceEn = "Yesterday I ___ to school.",
            options = listOf("go", "going", "went", "gone"),
            correctIndex = 2,
            explanationHi = "बीते हुए कल (Yesterday) के लिए क्रिया का 2nd form 'went' आता है।"
        ),
        LevelTestQuestion(
            id = 3,
            promptHi = "सोमवार को मिलने के लिए सही Preposition:",
            sentenceEn = "We will meet ___ Monday.",
            options = listOf("in", "on", "at", "to"),
            correctIndex = 1,
            explanationHi = "दिन (Monday, Sunday) और तारीख से पहले हमेशा 'on' का उपयोग होता है।"
        ),
        LevelTestQuestion(
            id = 4,
            promptHi = "निम्न में से कौन-सा वाक्य 100% सही है?",
            sentenceEn = "सही विकल्प चुनें:",
            options = listOf(
                "He did not went there.",
                "He did not go there.",
                "He not went there.",
                "He did no go there."
            ),
            correctIndex = 1,
            explanationHi = "'did' के साथ हमेशा क्रिया का पहला रूप (V1 = go) आता है, 'went' नहीं।"
        ),
        LevelTestQuestion(
            id = 5,
            promptHi = "ऑफिस ईमेल में विनम्र अनुरोध कैसे लिखें?",
            sentenceEn = "___, could you please share the report?",
            options = listOf("Give me fast", "Hi, kindly note", "Please revert back", "Excuse me"),
            correctIndex = 1,
            explanationHi = "प्रोफेशनल ईमेल में 'revert back' गलत है (सिर्फ reply/share लिखें) और 'kindly' विनम्र तरीका है।"
        )
    )

    val dailyTasks = listOf(
        DailyTask("words", "सुबह: 5 नए शब्द याद करें", "Flashcards से हिंदी अर्थ और उच्चारण सुनें", 25, 3),
        DailyTask("grammar", "दोपहर: 1 Visual Grammar नियम", "Tense या Preposition की आसान ट्रिक देखें", 20, 2),
        DailyTask("speak", "शाम: 2 मिनट बोलने का अभ्यास", "माइक पर 2 वाक्य बोलें और उच्चारण जाँचें", 30, 1),
        DailyTask("story", "रात: 1 कहानी पढ़ें और 1 ईमेल सुधारें", "Tap-to-Translate कहानी और Writing Coach", 25, 4)
    )

    val roleplayScenarios = listOf(
        RoleplayScenario(
            id = "interview",
            titleHi = "जॉब इंटरव्यू (Job Interview)",
            contextHi = "HR आपसे आपका परिचय और ताकत पूछ रहे हैं।",
            starterLineEn = "Hello! Tell me a little about yourself and your strengths.",
            starterLineHi = "नमस्ते! मुझे अपने बारे में और अपनी खूबियों के बारे में थोड़ा बताइए।",
            hintHi = "हिंदी हिंट: पहले अपना नाम, पढ़ाई और अपनी 1 अच्छी आदत (जैसे hard-working या quick learner) बोलें।",
            turns = listOf(
                RoleplayTurn(
                    speaker = "HR",
                    englishText = "That sounds good. Why do you want to join our company?",
                    hindiMeaning = "अच्छा लगा। आप हमारी कंपनी में क्यों जुड़ना चाहते हैं?",
                    hintHi = "बोलें: मुझे सीखने का शौक है और आपकी कंपनी में काम करके मैं अपने कौशल बढ़ा सकता हूँ।",
                    suggestedReplies = listOf(
                        "I want to learn new skills and grow with your team.",
                        "Your company has a great work culture, and I am eager to contribute."
                    )
                ),
                RoleplayTurn(
                    speaker = "HR",
                    englishText = "Great! When can you start if selected?",
                    hindiMeaning = "बहुत बढ़िया! अगर आपका चयन होता है तो आप कब से शुरू कर सकते हैं?",
                    hintHi = "बोलें: मैं तुरंत या अगले सोमवार से जुड़ सकता हूँ।",
                    suggestedReplies = listOf(
                        "I can join immediately from next Monday.",
                        "I am available to start as soon as required."
                    )
                )
            )
        ),
        RoleplayScenario(
            id = "shop",
            titleHi = "दुकान या बाज़ार में बातचीत",
            contextHi = "आप एक किताब या मोबाइल कवर खरीदने गए हैं।",
            starterLineEn = "Good morning! How can I help you today?",
            starterLineHi = "सुप्रभात! आज मैं आपकी क्या मदद कर सकता हूँ?",
            hintHi = "हिंदी हिंट: बोलें कि आप एक इंग्लिश ग्रामर की किताब या नोटबुक ढूँढ रहे हैं।",
            turns = listOf(
                RoleplayTurn(
                    speaker = "Shopkeeper",
                    englishText = "Sure, we have two options. Would you like the beginner guide?",
                    hindiMeaning = "जी हाँ, हमारे पास दो विकल्प हैं। क्या आपको शुरुआती गाइड चाहिए?",
                    hintHi = "बोलें: जी हाँ, कृपया कीमत बता दीजिए।",
                    suggestedReplies = listOf(
                        "Yes please. How much does it cost?",
                        "Can you show me the simple one and tell me the price?"
                    )
                )
            )
        ),
        RoleplayScenario(
            id = "doctor",
            titleHi = "डॉक्टर के पास (Doctor Visit)",
            contextHi = "आपको कल से हल्का बुखार और गले में खराश है।",
            starterLineEn = "Hello, please sit down. What problem are you facing?",
            starterLineHi = "नमस्ते, बैठिए। आपको क्या तकलीफ़ हो रही है?",
            hintHi = "हिंदी हिंट: बोलें 'I have had a mild fever since yesterday.' (मुझे कल से हल्का बुखार है)।",
            turns = listOf(
                RoleplayTurn(
                    speaker = "Doctor",
                    englishText = "Okay, let me check. Are you taking any medicine right now?",
                    hindiMeaning = "ठीक है, मैं जाँच करता हूँ। क्या आप अभी कोई दवाई ले रहे हैं?",
                    hintHi = "बोलें: नहीं डॉक्टर, मैंने अभी कोई दवाई नहीं ली है।",
                    suggestedReplies = listOf(
                        "No doctor, I have not taken any medicine yet.",
                        "Only warm water, no medicine so far."
                    )
                )
            )
        )
    )

    val pronunciationDrills = listOf(
        PronunciationDrill(
            id = "p1",
            focusTitleHi = "S / School ट्रिक (इस्कूल न बोलें)",
            sentenceEn = "I go to school every morning at seven.",
            phoneticHi = "आय गो टू स्कूल एवरी मॉर्निंग एट सेवेन",
            hindiMeaning = "मैं रोज़ सुबह सात बजे स्कूल जाता हूँ।",
            commonMistakeHi = "अक्सर छात्र 'School' को 'इस्कूल' या 'सकूल' बोलते हैं। 'S' की हल्की सी आवाज़ 'स्-कूल' से शुरू करें।"
        ),
        PronunciationDrill(
            id = "p2",
            focusTitleHi = "V और W का फर्क",
            sentenceEn = "We visited a very wonderful village.",
            phoneticHi = "वी विज़िटेड अ वेरी वंडरफुल विलेज",
            hindiMeaning = "हमने एक बहुत ही सुंदर गाँव की सैर की।",
            commonMistakeHi = "V बोलते समय ऊपर के दाँत नीचे के होंठ को छुएंगे (Very), जबकि W बोलते समय होंठ गोल होंगे (We, Wonderful)।"
        ),
        PronunciationDrill(
            id = "p3",
            focusTitleHi = "Sh बनाम S का अंतर",
            sentenceEn = "She sells fresh shoes in the shop.",
            phoneticHi = "शी सेल्स फ्रेश शूज़ इन द शॉप",
            hindiMeaning = "वह दुकान में नए जूते बेचती है।",
            commonMistakeHi = "'She' और 'Shoes' में 'श' की आवाज़ साफ़ बोलें, 'सी' या 'सूज़' नहीं।"
        ),
        PronunciationDrill(
            id = "p4",
            focusTitleHi = "आत्मविश्वास वाला परिचय",
            sentenceEn = "I am practicing English daily to speak fluently.",
            phoneticHi = "आय एम प्रैक्टिसिंग इंग्लिश डेली टू स्पीक फ्लुएंटली",
            hindiMeaning = "मैं धाराप्रवाह बोलने के लिए रोज़ अंग्रेज़ी का अभ्यास कर रहा हूँ।",
            commonMistakeHi = "'Fluently' को धीरे और साफ़ बोलें: फ्लू-एंट-ली।"
        )
    )

    val bridgeTraps = listOf(
        BridgeTrap(
            id = "b1",
            hindiThought = "मुझे भूख लगी है।",
            wrongLiteral = "I have hunger. / Hunger is coming to me.",
            naturalEnglish = "I am hungry.",
            reasonHi = "हिंदी में हम 'लगी है' कहते हैं, लेकिन English में अवस्था बताने के लिए 'I am + adjective' (hungry/thirsty/sleepy) आता है।"
        ),
        BridgeTrap(
            id = "b2",
            hindiThought = "मैं आपसे सहमत हूँ।",
            wrongLiteral = "I am agree with you.",
            naturalEnglish = "I agree with you.",
            reasonHi = "'Agree' खुद एक क्रिया (Verb) है। इसके साथ 'am/is/are' नहीं लगाया जाता।"
        ),
        BridgeTrap(
            id = "b3",
            hindiThought = "वो मेरा चचेरा भाई है।",
            wrongLiteral = "He is my cousin brother.",
            naturalEnglish = "He is my cousin.",
            reasonHi = "'Cousin' में भाई या बहन का अर्थ पहले से शामिल होता है, इसलिए पीछे brother/sister लगाना गलत है।"
        ),
        BridgeTrap(
            id = "b4",
            hindiThought = "कृपया वापस जवाब दें।",
            wrongLiteral = "Please revert back to me.",
            naturalEnglish = "Please reply to me. / Please get back to me.",
            reasonHi = "'Revert' का मतलब पहले ही 'वापस लौटना' होता है, इसलिए उसके साथ 'back' नहीं लगाते।"
        ),
        BridgeTrap(
            id = "b5",
            hindiThought = "आज मेरी तबीयत ठीक नहीं है।",
            wrongLiteral = "Today my health is down.",
            naturalEnglish = "I am not feeling well today.",
            reasonHi = "स्वास्थ्य के लिए English में 'feeling well' या 'under the weather' बोलना ज्यादा स्वाभाविक है।"
        ),
        BridgeTrap(
            id = "b6",
            hindiThought = "आपका शुभ नाम क्या है?",
            wrongLiteral = "What is your good name?",
            naturalEnglish = "May I know your name, please?",
            reasonHi = "'Good name' हिंदी के 'शुभ नाम' का सीधा अनुवाद है; असली English में 'May I know your name?' बोलते हैं।"
        )
    )

    val sampleHinglishPresets = listOf(
        "Kal main office late jaunga kyuki traffic hai",
        "Mujhe English bolne mein dar lagta hai",
        "Kya aap mujhe chutti de sakte ho",
        "Main roz subah jaldi uthta hun"
    )

    fun convertHinglishOffline(input: String): HinglishConversion {
        val clean = input.trim().lowercase()
        return when {
            clean.contains("office") && clean.contains("late") -> HinglishConversion(
                hinglishInput = input,
                simpleEn = "Tomorrow I will go to the office late because of traffic.",
                formalEn = "I may arrive late at the office tomorrow due to heavy traffic.",
                smartEn = "I'll be running a bit late for work tomorrow — traffic is quite heavy.",
                grammarTipHi = "भविष्य के लिए 'will / may' लगाएं और 'kyuki traffic hai' के लिए 'because of traffic' या 'due to traffic' बोलें।"
            )
            clean.contains("dar") || clean.contains("scared") || clean.contains("hesitat") -> HinglishConversion(
                hinglishInput = input,
                simpleEn = "I feel scared to speak in English.",
                formalEn = "I often hesitate while speaking English.",
                smartEn = "I get a little nervous when speaking English, though I'm working on it.",
                grammarTipHi = "'डर लगना / झिझकना' के लिए 'feel scared' या 'hesitate' सबसे सटीक शब्द हैं।"
            )
            clean.contains("chutti") || clean.contains("leave") -> HinglishConversion(
                hinglishInput = input,
                simpleEn = "Can you give me leave today?",
                formalEn = "Could you please grant me leave for today?",
                smartEn = "Would it be possible for me to take the day off today?",
                grammarTipHi = "विनम्र निवेदन के लिए 'Can' से बेहतर 'Could you please' या 'Would it be possible' होता है।"
            )
            clean.contains("subah") || clean.contains("uthta") || clean.contains("wake") -> HinglishConversion(
                hinglishInput = input,
                simpleEn = "I wake up early every morning.",
                formalEn = "I maintain a habit of waking up early every morning.",
                smartEn = "I'm an early riser — I wake up early every day.",
                grammarTipHi = "रोज़ की आदत बताने के लिए हमेशा Present Simple (I wake up) का उपयोग करें, 'I am waking up' नहीं।"
            )
            else -> {
                val words = clean
                    .replace("kal", "tomorrow")
                    .replace("aaj", "today")
                    .replace("main", "I")
                    .replace("mujhe", "I")
                    .replace("tum", "you")
                    .replace("aap", "you")
                    .replace("ghar", "home")
                    .replace("kaam", "work")
                    .replace("paani", "water")
                    .replace("khana", "food")
                    .replace("chahiye", "need")
                    .replace("karna hai", "want to do")
                    .replace("jaunga", "will go")
                    .replace("aaunga", "will come")
                HinglishConversion(
                    hinglishInput = input,
                    simpleEn = "Simple: \"${words.replaceFirstChar { it.uppercase() }}\" — (Try adding subject + verb + object)",
                    formalEn = "Formal: Please express your thought clearly using Subject + Helping Verb + Main Verb.",
                    smartEn = "Tip: You can connect your own free Gemini Key in Settings for unlimited custom Hinglish AI conversions!",
                    grammarTipHi = "वाक्य बनाने का गोल्डन नियम: पहले कर्ता (I/You/He) + फिर काम (Verb) + फिर बाकी बात (Object)।"
                )
            }
        }
    }

    val grammarConcepts = listOf(
        GrammarConcept(
            id = "g1",
            category = "Tenses",
            titleHi = "Present Simple (रोज़ की आदत)",
            formulaEn = "Subject + V1 (s/es for He/She)",
            timelineLabelHi = "हमेशा / रोज़ (Past ◄── [NOW] ──► Future)",
            exampleEn = "She speaks English clearly.",
            exampleHi = "वह साफ़ अंग्रेज़ी बोलती है।",
            trickHi = "हिंदी वाक्य के अंत में 'ता है / ती है / ते हैं' आए तो I/You/We/They के साथ सीधा V1 लगाएं, He/She/It के साथ s/es जोड़ें।"
        ),
        GrammarConcept(
            id = "g2",
            category = "Tenses",
            titleHi = "Past Simple (बीती हुई बात)",
            formulaEn = "Subject + V2  |  Negative: did not + V1",
            timelineLabelHi = "बीता हुआ समय ([PAST ●] ──── NOW ──── Future)",
            exampleEn = "I watched a movie, but I did not sleep late.",
            exampleHi = "मैंने फिल्म देखी, लेकिन मैं देर से नहीं सोया।",
            trickHi = "याद रखें: सकारात्मक वाक्य में V2 (watched), लेकिन 'did not' आते ही वापस V1 (sleep)!"
        ),
        GrammarConcept(
            id = "g3",
            category = "Tenses",
            titleHi = "Future Simple (आने वाला कल)",
            formulaEn = "Subject + will + V1",
            timelineLabelHi = "आने वाला समय (Past ──── NOW ──── [● FUTURE])",
            exampleEn = "I will practice for 10 minutes tonight.",
            exampleHi = "मैं आज रात 10 मिनट अभ्यास करूँगा।",
            trickHi = "'गा / गे / गी' के लिए सबके साथ बिना सोचे 'will + V1' लगा दें।"
        ),
        GrammarConcept(
            id = "g4",
            category = "Prepositions",
            titleHi = "IN, ON, AT का पक्का नियम",
            formulaEn = "IN (महीना/साल/अंदर) · ON (दिन/तारीख/सतह) · AT (सटीक समय/जगह)",
            timelineLabelHi = "बड़ा घेरा (IN) ➔ मध्यम (ON) ➔ सटीक बिंदु (AT)",
            exampleEn = "The exam is in July, on Monday, at 9:00 AM.",
            exampleHi = "परीक्षा जुलाई में, सोमवार को, सुबह 9 बजे है।",
            trickHi = "साल/महीना = IN (in 2026), दिन/तारीख = ON (on 15 August), घड़ी का समय = AT (at 6 PM)।"
        ),
        GrammarConcept(
            id = "g5",
            category = "Modals",
            titleHi = "Can, Should और Must का फर्क",
            formulaEn = "Can (सकता है) · Should (चाहिए) · Must (ज़रूर चाहिए)",
            timelineLabelHi = "क्षमता (Can) · सलाह (Should) · ज़रूरी नियम (Must)",
            exampleEn = "You can learn fast if you should practice, and you must believe in yourself.",
            exampleHi = "आप तेज़ी से सीख सकते हैं, आपको अभ्यास करना चाहिए और खुद पर भरोसा ज़रूर रखना चाहिए।",
            trickHi = "इन तीनों के बाद हमेशा क्रिया का पहला रूप (V1) ही आता है (जैसे can go, should read)।"
        )
    )

    val flashcards = listOf(
        FlashcardItem("f1", "Confident", "कॉन्फिडेंट", "आत्मविश्वासी", "Speak slowly and look confident.", "धीरे बोलें और आत्मविश्वासी दिखें।", "Daily Life"),
        FlashcardItem("f2", "Hesitate", "हेज़िटेट", "झिझकना / हिचकिचाना", "Do not hesitate to ask questions.", "सवाल पूछने में हिचकिचाएं नहीं।", "Speaking"),
        FlashcardItem("f3", "Improve", "इम्प्रूव", "सुधार करना", "Daily reading will improve your English.", "रोज़ पढ़ने से आपकी अंग्रेज़ी सुधरेगी।", "Study"),
        FlashcardItem("f4", "Opportunity", "ऑपर्च्युनिटी", "अवसर / मौका", "Every problem brings a new opportunity.", "हर समस्या एक नया अवसर लाती है।", "Career"),
        FlashcardItem("f5", "Polite", "पोलाइट", "विनम्र / सभ्य", "Always be polite with elders and colleagues.", "बड़ों और सहकर्मियों से हमेशा विनम्र रहें।", "Office"),
        FlashcardItem("f6", "Consistent", "कंसिस्टेंट", "लगातार / नियमित", "Be consistent for 30 days to see magic.", "जादू देखने के लिए 30 दिन नियमित रहें।", "Mindset")
    )

    val confusingQuizzes = listOf(
        ConfusingWordQuiz(
            id = "q1",
            wordA = "Accept",
            meaningAHi = "स्वीकार करना",
            wordB = "Except",
            meaningBHi = "के अलावा / को छोड़कर",
            questionSentence = "Everyone came to class ___ Ravi.",
            correctWord = "Except",
            explanationHi = "यहाँ अर्थ है 'रवि को छोड़कर सब क्लास में आए', इसलिए 'Except' सही है।"
        ),
        ConfusingWordQuiz(
            id = "q2",
            wordA = "Principal",
            meaningAHi = "प्रधानाचार्य / मुख्य",
            wordB = "Principle",
            meaningBHi = "सिद्धांत / नियम",
            questionSentence = "Honesty is an important ___ in life.",
            correctWord = "Principle",
            explanationHi = "ईमानदारी जीवन का एक 'सिद्धांत' है, इसलिए अंत में '-le' वाला Principle आएगा।"
        ),
        ConfusingWordQuiz(
            id = "q3",
            wordA = "Affect",
            meaningAHi = "असर डालना (Verb)",
            wordB = "Effect",
            meaningBHi = "परिणाम / असर (Noun)",
            questionSentence = "Lack of sleep can ___ your focus.",
            correctWord = "Affect",
            explanationHi = "यहाँ 'असर डालना' एक काम (Verb) है, इसलिए 'Affect' सही होगा।"
        ),
        ConfusingWordQuiz(
            id = "q4",
            wordA = "Advice",
            meaningAHi = "सलाह (Noun)",
            wordB = "Advise",
            meaningBHi = "सलाह देना (Verb)",
            questionSentence = "My teacher gave me good ___.",
            correctWord = "Advice",
            explanationHi = "'अच्छी सलाह दी' में सलाह एक संज्ञा (Noun) है — इसमें 'c' वाला Advice आता है।"
        )
    )

    val stories = listOf(
        StoryItem(
            id = "s1",
            titleEn = "Aarav's First Stage Speech",
            titleHi = "आरव का मंच पर पहला भाषण",
            levelLabel = "Beginner · 2 Min Read",
            paragraphsEn = listOf(
                "Aarav studied in a Hindi medium school in a small town. He always felt nervous when someone spoke English quickly.",
                "One day, his teacher encouraged him to prepare a short speech. Aarav practiced five sentences in front of a mirror every morning.",
                "On Friday, Aarav stood on the stage with courage. He spoke slowly and clearly, and the entire hall clapped with pride."
            ),
            summaryHi = "एक छोटे शहर के हिंदी माध्यम के छात्र आरव ने रोज़ आईने के सामने 5 वाक्यों का अभ्यास करके मंच का डर जीत लिया।",
            glossary = mapOf(
                "nervous" to GlossaryWord("Nervous", "नर्वस", "घबराया हुआ", "Adjective", "Don't feel nervous before an exam."),
                "encouraged" to GlossaryWord("Encouraged", "एन्करेज्ड", "प्रोत्साहित किया / हिम्मत बढ़ाई", "Verb (Past)", "My parents encouraged me to try again."),
                "prepare" to GlossaryWord("Prepare", "प्रिपेयर", "तैयारी करना", "Verb", "Let's prepare for tomorrow's test."),
                "mirror" to GlossaryWord("Mirror", "मिरर", "आईना / शीशा", "Noun", "Practice speaking in front of a mirror."),
                "courage" to GlossaryWord("Courage", "करेज", "साहस / हिम्मत", "Noun", "It takes courage to speak a new language."),
                "entire" to GlossaryWord("Entire", "एन्टायर", "पूरा / सारा", "Adjective", "The entire class listened quietly."),
                "pride" to GlossaryWord("Pride", "प्राइड", "गर्व", "Noun", "She smiled with pride.")
            )
        ),
        StoryItem(
            id = "s2",
            titleEn = "The Smart Interview Preparation",
            titleHi = "समझदारी भरी इंटरव्यू तैयारी",
            levelLabel = "Intermediate · 2 Min Read",
            paragraphsEn = listOf(
                "Priya wanted an office job, so she improved her vocabulary and stopped translating every word from Hindi.",
                "During the interview, she was polite and honest. Whenever she needed a second to think, she took a calm breath instead of panicking.",
                "The manager appreciated her clarity and confidence, and offered her the role on the same afternoon."
            ),
            summaryHi = "प्रिया ने शब्द-दर-शब्द हिंदी अनुवाद छोड़ने और शांत रहकर बोलने की कला से अपना पहला ऑफिस इंटरव्यू पास किया।",
            glossary = mapOf(
                "improved" to GlossaryWord("Improved", "इम्प्रूव्ड", "बेहतर बनाया / सुधारा", "Verb (Past)", "He improved his handwriting."),
                "vocabulary" to GlossaryWord("Vocabulary", "वोकैबुलरी", "शब्दावली", "Noun", "Build vocabulary by learning 5 words daily."),
                "translating" to GlossaryWord("Translating", "ट्रांसलेटिंग", "अनुवाद करना", "Verb (-ing)", "Stop translating word-by-word."),
                "polite" to GlossaryWord("Polite", "पोलाइट", "विनम्र", "Adjective", "Be polite when speaking to seniors."),
                "calm" to GlossaryWord("Calm", "काम (L साइलेंट)", "शांत", "Adjective", "Stay calm and speak clearly."),
                "appreciated" to GlossaryWord("Appreciated", "अप्रिशिएटेड", "सराहना की / तारीफ़ की", "Verb (Past)", "The teacher appreciated her effort."),
                "clarity" to GlossaryWord("Clarity", "क्लैरिटी", "स्पष्टता / साफ़ बात", "Noun", "Clarity matters more than fast speaking.")
            )
        )
    )

    val writingTemplates = listOf(
        WritingTemplate(
            id = "w1",
            titleHi = "1 दिन की छुट्टी का ईमेल (Leave Email)",
            category = "Office / College",
            draftText = "Subject: Request for one day leave\n\nRespected Sir/Madam,\n\nI am not feeling well today, so I will not be able to attend the office/class. Kindly grant me leave for today (1 day).\n\nThank you,\nYour Name"
        ),
        WritingTemplate(
            id = "w2",
            titleHi = "नौकरी के लिए आवेदन ईमेल (Job Application)",
            category = "Career",
            draftText = "Subject: Application for Junior Executive Role\n\nDear Hiring Manager,\n\nI am writing to apply for the Junior Executive position. I am a quick learner with strong dedication and good communication skills. Please find my resume attached.\n\nWarm regards,\nYour Name"
        ),
        WritingTemplate(
            id = "w3",
            titleHi = "गलतियों वाला वाक्य जाँचें (Test Mistake Fixer)",
            category = "Practice",
            draftText = "Hello sir, I did not went to college yesterday because I am agree with my cousin brother. Please revert back to me."
        )
    )

    fun analyzeWritingOffline(text: String): List<WritingCorrection> {
        val findings = mutableListOf<WritingCorrection>()
        val lower = text.lowercase()

        if (lower.contains("did not went") || lower.contains("didn't went")) {
            findings.add(WritingCorrection("did not went", "did not go", "'did' के साथ हमेशा V1 ('go') आता है, 'went' नहीं।"))
        }
        if (lower.contains("i am agree")) {
            findings.add(WritingCorrection("I am agree", "I agree", "'Agree' एक क्रिया है, इसके पहले 'am' नहीं लगाते।"))
        }
        if (lower.contains("cousin brother") || lower.contains("cousin sister")) {
            findings.add(WritingCorrection("cousin brother/sister", "cousin", "सिर्फ 'cousin' काफी है; पीछे brother या sister लगाना गलत है।"))
        }
        if (lower.contains("revert back")) {
            findings.add(WritingCorrection("revert back", "reply / get back", "'Revert' का मतलब पहले से वापस आना होता है, 'back' न लगाएं।"))
        }
        if (lower.contains("more better")) {
            findings.add(WritingCorrection("more better", "much better", "'Better' खुद तुलनात्मक शब्द है, इसके आगे 'more' नहीं लगता।"))
        }
        if (lower.contains("discuss about")) {
            findings.add(WritingCorrection("discuss about", "discuss", "'Discuss' के बाद 'about' नहीं लगता (जैसे: Let's discuss the plan)।"))
        }
        if (lower.contains("good name")) {
            findings.add(WritingCorrection("what is your good name", "may I know your name", "English में 'good name' नहीं बल्कि 'your name' बोलते हैं।"))
        }
        if (lower.contains("myself ")) {
            findings.add(WritingCorrection("Myself [Name]", "I am [Name] / My name is [Name]", "परिचय देते समय 'Myself Rahul' बोलना गलत है; 'I am Rahul' बोलें।"))
        }

        if (findings.isEmpty()) {
            findings.add(
                WritingCorrection(
                    original = "General Review",
                    corrected = "आपका वाक्य साफ दिख रहा है! (No common literal-Hindi traps found)",
                    reasonHi = "बहुत बढ़िया! बड़ी हिंदी-अनुवाद गलतियाँ नहीं मिलीं। और गहरी जाँच के लिए आप Settings में फ्री Gemini Key जोड़ सकते हैं।"
                )
            )
        }
        return findings
    }
}
