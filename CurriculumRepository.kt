package com.englishseekho.pro.data

object CurriculumRepository {

    val levelTestQuestions = listOf(
        LevelTestQuestion(1, "सही वाक्य चुनें (मुझे भूख लगी है):", "___ hungry right now.", listOf("I have", "I am", "I do", "Me is"), 1, "भूख/प्यास/खुशी महसूस करने के लिए 'I am' लगता है, 'I have' नहीं।"),
        LevelTestQuestion(2, "कल मैं स्कूल गया था — सही शब्द भरें:", "Yesterday I ___ to school.", listOf("go", "going", "went", "gone"), 2, "बीते हुए कल (Yesterday) के लिए क्रिया का 2nd form 'went' आता है।"),
        LevelTestQuestion(3, "सोमवार को मिलने के लिए सही Preposition:", "We will meet ___ Monday.", listOf("in", "on", "at", "to"), 1, "दिन (Monday, Sunday) और तारीख से पहले हमेशा 'on' का उपयोग होता है।"),
        LevelTestQuestion(4, "निम्न में से कौन-सा वाक्य 100% सही है?", "सही विकल्प चुनें:", listOf("He did not went there.", "He did not go there.", "He not went there.", "He did no go there."), 1, "'did' के साथ हमेशा क्रिया का पहला रूप (V1 = go) आता है, 'went' नहीं।"),
        LevelTestQuestion(5, "ऑफिस ईमेल में विनम्र अनुरोध कैसे लिखें?", "___, could you please share the report?", listOf("Give me fast", "Hi, kindly note", "Please revert back", "Excuse me"), 1, "प्रोफेशनल ईमेल में 'revert back' गलत है (सिर्फ reply/share लिखें) और 'kindly' विनम्र तरीका है।")
    )

    val dailyTasks = listOf(
        DailyTask("words", "सुबह: 5 नए शब्द याद करें", "Flashcards व Verb Forms से अभ्यास करें", 25, 4),
        DailyTask("game", "दोपहर: 1 Sentence Builder गेम जीतें", "खेल-खेल में वाक्य बनाना सीखें", 30, 1),
        DailyTask("speak", "शाम: 2 मिनट बोलने का अभ्यास", "माइक पर वाक्य बोलें और उच्चारण जाँचें", 30, 2),
        DailyTask("grammar", "रात: 1 Visual Grammar व कहानी पढ़ें", "Tap-to-Translate कहानी और Bridge नियम", 25, 3)
    )

    val thirtyDayCourse = listOf(
        DayCourseLesson(1, "सप्ताह 1: ज़ीरो से शुरुआत", "दिन 1: आत्मविश्वास से अपना परिचय देना", "पहली मुलाकात में साफ और सरल परिचय बोलना सीखें।", listOf("Hello, my name is Aarav." to "नमस्ते, मेरा नाम आरव है।", "I am from a small town in India." to "मैं भारत के एक छोटे शहर से हूँ।", "I am learning English step by step." to "मैं कदम-दर-कदम अंग्रेज़ी सीख रहा हूँ।"), "'Myself Rahul' बोलना गलत है — हमेशा 'I am Rahul' या 'My name is Rahul' बोलें।"),
        DayCourseLesson(2, "सप्ताह 1: ज़ीरो से शुरुआत", "दिन 2: सुबह से रात तक अभिवादन", "समय के अनुसार सही नमस्ते और विदाई बोलना।", listOf("Good morning! How are you doing?" to "सुप्रभात! आप कैसे हैं?", "I am doing well, thank you." to "मैं ठीक हूँ, धन्यवाद।", "Have a wonderful day ahead!" to "आपका दिन शुभ हो!"), "दोपहर 12 से शाम 5 बजे तक 'Good afternoon' और शाम 5 के बाद 'Good evening' बोलें।"),
        DayCourseLesson(3, "सप्ताह 1: ज़ीरो से शुरुआत", "दिन 3: Is, Am, Are का पक्का इस्तेमाल", "अवस्था और पहचान बताने वाले सबसे जरूरी वाक्य।", listOf("I am ready for today's lesson." to "मैं आज के पाठ के लिए तैयार हूँ।", "She is my elder sister." to "वह मेरी बड़ी बहन है।", "We are very excited about this." to "हम इसे लेकर बहुत उत्साहित हैं।"), "I के साथ हमेशा am, एकवचन के साथ is, और बहुवचन के साथ are लगाएं।"),
        DayCourseLesson(4, "सप्ताह 1: ज़ीरो से शुरुआत", "दिन 4: Has और Have (पास होना)", "चीज़ें, रिश्ते या समय अपने पास होना बताना।", listOf("I have two notebooks in my bag." to "मेरे बैग में दो कॉपियाँ हैं।", "He has a lot of patience." to "उसके पास बहुत धैर्य है।", "Do you have a spare pen?" to "क्या आपके पास एक अतिरिक्त पेन है?"), "'I have hunger' कभी न बोलें — भूख के लिए 'I am hungry' ही सही है।"),
        DayCourseLesson(5, "सप्ताह 1: ज़ीरो से शुरुआत", "दिन 5: विनम्र निवेदन (Please & Kindly)", "किसी से प्यार से मदद या कोई चीज़ माँगना।", listOf("Could you please repeat that slowly?" to "क्या आप कृपया उसे धीरे दोहरा सकते हैं?", "Please pass me the water bottle." to "कृपया मुझे पानी की बोतल दे दीजिए।", "Thank you so much for your help." to "आपकी मदद के लिए बहुत धन्यवाद।"), "'Give me' के बजाय 'Could you please give me' बोलने से भाषा सभ्य लगती है।"),
        DayCourseLesson(6, "सप्ताह 1: ज़ीरो से शुरुआत", "दिन 6: दैनिक दिनचर्या (Daily Routine)", "सुबह उठने से रात को सोने तक की आदतें बताना।", listOf("I wake up at six in the morning." to "मैं सुबह छह बजे उठता हूँ।", "Then I revise five English words." to "फिर मैं पाँच अंग्रेज़ी शब्द दोहराता हूँ।", "I go to bed around ten PM." to "मैं रात करीब दस बजे सो जाता हूँ।"), "रोज़ की आदत में हमेशा Verb का पहला रूप (wake, read, eat) लगाएं।"),
        DayCourseLesson(7, "सप्ताह 1: ज़ीरो से शुरुआत", "दिन 7: सप्ताह 1 रिवीजन व 5-वाक्य भाषण", "बिना रुके लगातार 5 वाक्य बोलने का अभ्यास।", listOf("I started learning English seven days ago." to "मैंने सात दिन पहले अंग्रेज़ी सीखना शुरू किया।", "Now I do not feel afraid of mistakes." to "अब मुझे गलतियों से डर नहीं लगता।", "Consistency is my biggest strength." to "नियमितता मेरी सबसे बड़ी ताकत है।"), "गलती होने पर रुकें नहीं — मुस्कुराकर आगे बढ़ें!"),

        DayCourseLesson(8, "सप्ताह 2: रोज़मर्रा के टेंस और सवाल", "दिन 8: सवाल पूछना (What, Where, When)", "सही तरीके से जानकारी माँगना सीखें।", listOf("Where is the nearest bus stop?" to "सबसे नज़दीकी बस स्टॉप कहाँ है?", "What time does the class start?" to "क्लास कितने बजे शुरू होती है?", "When will you reach home?" to "आप घर कब पहुँचेंगे?"), "सवाल पूछते समय 'What you want?' नहीं, 'What do you want?' बोलें।"),
        DayCourseLesson(9, "सप्ताह 2: रोज़मर्रा के टेंस और सवाल", "दिन 9: Why और How से बात बढ़ाना", "बातचीत में कारण और तरीका पूछना।", listOf("Why are you looking so happy today?" to "आज आप इतने खुश क्यों दिख रहे हैं?", "How did you learn this so fast?" to "आपने यह इतनी जल्दी कैसे सीखा?", "How long does it take to reach?" to "पहुँचने में कितना समय लगता है?"), "'How much' कीमत/मात्रा और 'How many' गिनती वाली चीज़ों के लिए आता है।"),
        DayCourseLesson(10, "सप्ताह 2: रोज़मर्रा के टेंस और सवाल", "दिन 10: बीती हुई बातें (Past Simple)", "कल या पिछले हफ्ते क्या हुआ, साफ बताना।", listOf("Yesterday I met my old school friend." to "कल मैं अपने पुराने स्कूल मित्र से मिला।", "I did not waste time on my phone." to "मैंने फोन पर समय बर्बाद नहीं किया।", "We talked about our future plans." to "हमने अपने भविष्य की योजनाओं पर बात की।"), "'did not' के बाद हमेशा V1 (waste/go/eat) ही आता है।"),
        DayCourseLesson(11, "सप्ताह 2: रोज़मर्रा के टेंस और सवाल", "दिन 11: आने वाला कल (Future Plans)", "भविष्य के इरादे और वादे बताना।", listOf("Tomorrow I will practice speaking for fifteen minutes." to "कल मैं पंद्रह मिनट बोलने का अभ्यास करूँगा।", "I am going to start a new chapter." to "मैं एक नया अध्याय शुरू करने जा रहा हूँ।", "We will surely succeed one day." to "हम एक दिन ज़रूर सफल होंगे।"), "तय योजना के लिए 'going to' और तुरंत निर्णय के लिए 'will' बोलना ज्यादा स्वाभाविक है।"),
        DayCourseLesson(12, "सप्ताह 2: रोज़मर्रा के टेंस और सवाल", "दिन 12: IN, ON, AT का जादू", "समय और स्थान बताने में कभी गलती न करना।", listOf("The train arrives at seven thirty." to "ट्रेन साढ़े सात बजे आती है।", "My exam is on Friday morning." to "मेरी परीक्षा शुक्रवार सुबह को है।", "I live in a peaceful neighbourhood." to "मैं एक शांत मोहल्ले में रहता हूँ।"), "समय = at, दिन = on, शहर/महीना/साल = in।"),
        DayCourseLesson(13, "सप्ताह 2: रोज़मर्रा के टेंस और सवाल", "दिन 13: दुकान व बाज़ार में मोलभाव", "कीमत पूछना और सही सामान माँगना।", listOf("Excuse me, how much is this shirt for?" to "क्षमा करें, यह शर्ट कितने की है?", "Do you have a smaller size in blue?" to "क्या आपके पास नीले रंग में छोटा साइज है?", "Can I pay using UPI?" to "क्या मैं यूपीआई से भुगतान कर सकता हूँ?"), "'Excuse me' कहकर बात शुरू करने से दुकानदार पूरा ध्यान देते हैं।"),
        DayCourseLesson(14, "सप्ताह 2: रोज़मर्रा के टेंस और सवाल", "दिन 14: फोन कॉल पर प्रोफेशनल बातचीत", "फोन उठाने से रखने तक के सटीक वाक्य।", listOf("Hello, may I know who is speaking?" to "नमस्ते, क्या मैं जान सकता हूँ कौन बोल रहा है?", "Your voice is breaking, could you repeat?" to "आपकी आवाज़ कट रही है, क्या आप दोहराएंगे?", "I will call you back in ten minutes." to "मैं आपको दस मिनट में वापस कॉल करूँगा।"), "फोन पर 'Wait' की जगह 'Hold on a moment, please' बोलना ज्यादा विनम्र है।"),
        DayCourseLesson(15, "सप्ताह 2: रोज़मर्रा के टेंस और सवाल", "दिन 15: सप्ताह 2 प्रैक्टिस चैलेंज", "पिछले सप्ताह की सारी संरचनाओं का मिश्रण।", listOf("Last week I learned how to ask polite questions." to "पिछले हफ्ते मैंने विनम्र सवाल पूछना सीखा।", "Now I can talk clearly on phone calls." to "अब मैं फोन कॉल पर साफ बात कर सकता हूँ।", "Next week I will focus on fluency." to "अगले हफ्ते मैं प्रवाह (fluency) पर ध्यान दूँगा।"), "शीशे के सामने खड़े होकर रोज़ 3 वाक्य ज़ोर से बोलें।"),

        DayCourseLesson(16, "सप्ताह 3: आत्मविश्वास और फ्लुएंसी", "दिन 16: Can, Should और Must", "क्षमता, सलाह और ज़रूरी नियम बताना।", listOf("I can understand English easily now." to "मैं अब अंग्रेज़ी आसानी से समझ सकता हूँ।", "You should drink more water daily." to "आपको रोज़ ज्यादा पानी पीना चाहिए।", "We must respect everyone's time." to "हमें हर किसी के समय का सम्मान ज़रूर करना चाहिए।"), "इन शब्दों के बाद कभी 'to' न लगाएं ('can to go' ❌ ➔ 'can go' ✅)।"),
        DayCourseLesson(17, "सप्ताह 3: आत्मविश्वास और फ्लुएंसी", "दिन 17: अभी चल रहा काम (Continuous)", "इस समय क्या हो रहा है, वह बताना।", listOf("Right now I am reading an English story." to "अभी मैं एक अंग्रेज़ी कहानी पढ़ रहा हूँ।", "It is raining heavily outside." to "बाहर तेज़ बारिश हो रही है।", "They are preparing for the interview." to "वे इंटरव्यू की तैयारी कर रहे हैं।"), "जब काम आँखों के सामने चल रहा हो तो is/am/are + verb-ing लगाएं।"),
        DayCourseLesson(18, "सप्ताह 3: आत्मविश्वास और फ्लुएंसी", "दिन 18: अभी-अभी पूरा हुआ काम (Present Perfect)", "Has/Have + V3 का सही उपयोग।", listOf("I have completed my homework already." to "मैंने अपना होमवर्क पहले ही पूरा कर लिया है।", "Have you ever visited Delhi?" to "क्या आप कभी दिल्ली गए हैं?", "She has improved her pronunciation a lot." to "उसने अपना उच्चारण बहुत सुधार लिया है।"), "अक्सर छात्र 'I have did' बोल देते हैं — सही रूप 'I have done' (V3) है।"),
        DayCourseLesson(19, "सप्ताह 3: आत्मविश्वास और फ्लुएंसी", "दिन 19: डॉक्टर और अस्पताल में बात", "बीमारी और लक्षण साफ़-साफ़ बताना।", listOf("I have had a headache since morning." to "मुझे सुबह से सिरदर्द हो रहा है।", "Should I take this tablet after meals?" to "क्या मुझे यह गोली खाने के बाद लेनी चाहिए?", "Thank you doctor, I feel much better now." to "धन्यवाद डॉक्टर, अब मुझे काफी बेहतर लग रहा है।"), "लंबे समय से चल रही परेशानी के लिए 'since morning' या 'for two days' लगाएं।"),
        DayCourseLesson(20, "सप्ताह 3: आत्मविश्वास और फ्लुएंसी", "दिन 20: ट्रेन, बस और यात्रा के वाक्य", "टिकट, प्लेटफॉर्म और रास्ता पूछना।", listOf("Which platform does the Lucknow train arrive on?" to "लखनऊ की ट्रेन किस प्लेटफॉर्म पर आती है?", "Is this seat taken or vacant?" to "क्या यह सीट भरी है या खाली है?", "Could you please tell me when my stop comes?" to "कृपया मुझे बता देंगे जब मेरा स्टॉप आए?"), "'Empty' से ज्यादा सीट के लिए 'Vacant' या 'Available' बोला जाता है।"),
        DayCourseLesson(21, "सप्ताह 3: आत्मविश्वास और फ्लुएंसी", "दिन 21: अपनी राय और सहमति देना", "बिना झिझक अपनी बात रखना।", listOf("In my opinion, daily practice matters most." to "मेरी राय में, रोज़ का अभ्यास सबसे ज्यादा मायने रखता है।", "I completely agree with your point." to "मैं आपकी बात से पूरी तरह सहमत हूँ।", "That is a very interesting idea." to "यह बहुत ही दिलचस्प विचार है।"), "'I am agree' ❌ बिल्कुल न बोलें — हमेशा 'I agree' ✅ बोलें।"),
        DayCourseLesson(22, "सप्ताह 3: आत्मविश्वास और फ्लुएंसी", "दिन 22: दोस्तों के साथ सहज बातचीत", "छोटी-मोटी गपशप (Small Talk) के स्मार्ट वाक्य।", listOf("Long time no see! Where have you been?" to "बहुत दिनों बाद दिखे! कहाँ थे?", "Nothing much, just keeping busy with studies." to "कुछ खास नहीं, बस पढ़ाई में व्यस्त हूँ।", "Let us catch up this weekend." to "चलो इस सप्ताहांत मिलते हैं।"), "'Catch up' का मतलब है पुराने दोस्तों से मिलकर हालचाल जानना।"),
        DayCourseLesson(23, "सप्ताह 3: आत्मविश्वास और फ्लुएंसी", "दिन 23: अटकने पर समय लेने वाले शब्द", "सोचते वक्त घबराएं नहीं — स्मार्ट कनेक्टर बोलें।", listOf("Let me think about that for a second." to "मुझे एक सेकंड इसके बारे में सोचने दीजिए।", "What I mean to say is very simple." to "मेरा कहने का मतलब बहुत सरल है।", "Actually, there are two main reasons for this." to "दरअसल, इसके दो मुख्य कारण हैं।"), "'Umm...' करने से अच्छा है 'Actually...' या 'Let me think...' बोलना।"),

        DayCourseLesson(24, "सप्ताह 4: जॉब और प्रोफेशनल इंग्लिश", "दिन 24: ऑफिस ईमेल की सही शुरुआत व अंत", "छुट्टी और काम के ईमेल की प्रोफेशनल शैली।", listOf("I am writing to request one day of leave." to "मैं एक दिन की छुट्टी के अनुरोध के लिए लिख रहा हूँ।", "Please find the attached document for your review." to "कृपया अपनी समीक्षा के लिए संलग्न दस्तावेज़ देखें।", "Looking forward to hearing from you soon." to "आपकी जल्द प्रतिक्रिया की प्रतीक्षा रहेगी।"), "'Please do the needful' और 'revert back' पुराने हैं — उनकी जगह 'Please reply' लिखें।"),
        DayCourseLesson(25, "सप्ताह 4: जॉब और प्रोफेशनल इंग्लिश", "दिन 25: इंटरव्यू में 'Tell me about yourself'", "60 सेकंड का दमदार परिचय तैयार करना।", listOf("I am a Commerce graduate with strong computer skills." to "मैं मजबूत कंप्यूटर कौशल के साथ कॉमर्स स्नातक हूँ।", "My greatest strength is my willingness to learn." to "मेरी सबसे बड़ी ताकत सीखने की इच्छा है।", "I am looking for a role where I can grow." to "मैं ऐसी भूमिका की तलाश में हूँ जहाँ मैं आगे बढ़ सकूँ।"), "इंटरव्यू में परिवार की पूरी कहानी के बजाय अपनी पढ़ाई, स्किल और लक्ष्य बताएं।"),
        DayCourseLesson(26, "सप्ताह 4: जॉब और प्रोफेशनल इंग्लिश", "दिन 26: अपनी कमज़ोरी और ताकत बताना", "इंटरव्यू के मुश्किल सवालों का स्मार्ट जवाब।", listOf("I used to hesitate in English, so I practiced daily." to "मैं पहले अंग्रेज़ी में झिझकता था, इसलिए मैंने रोज़ अभ्यास किया।", "I work well both independently and in a team." to "मैं अकेले और टीम दोनों के साथ अच्छा काम करता हूँ।", "I take feedback positively to improve myself." to "मैं खुद को बेहतर बनाने के लिए फीडबैक को सकारात्मक रूप से लेता हूँ।"), "अपनी कमज़ोरी के साथ यह ज़रूर जोड़ें कि आप उसे सुधारने के लिए क्या कर रहे हैं।"),
        DayCourseLesson(27, "सप्ताह 4: जॉब और प्रोफेशनल इंग्लिश", "दिन 27: मीटिंग में विनम्र असहमति", "किसी की बात काटे बिना अपना विचार रखना।", listOf("I see your point, however I have a suggestion." to "मैं आपकी बात समझता हूँ, हालांकि मेरा एक सुझाव है।", "Could we look at this from another angle?" to "क्या हम इसे दूसरे नज़रिए से देख सकते हैं?", "Let me clarify that part briefly." to "मुझे उस हिस्से को संक्षेप में स्पष्ट करने दें।"), "सीधे 'You are wrong' कभी न बोलें — 'I have a slightly different view' बोलें।"),
        DayCourseLesson(28, "सप्ताह 4: जॉब और प्रोफेशनल इंग्लिश", "दिन 28: Formal बनाम Informal भाषा", "कहाँ दोस्त जैसी और कहाँ ऑफिस जैसी बात करनी है।", listOf("Informal: Thanks a lot! | Formal: I appreciate your support." to "अंतर: धन्यवाद बोलने का दोस्ताना बनाम ऑफिस तरीका", "Informal: Tell me soon | Formal: Please let me know at your earliest." to "अंतर: जल्दी बताने का विनम्र अनुरोध", "Informal: Sorry I am late | Formal: Apologies for the delay." to "अंतर: देर होने पर माफी माँगना"), "असली प्रोफेशनल वही है जो जगह के हिसाब से सही शब्द चुने।"),
        DayCourseLesson(29, "सप्ताह 4: जॉब और प्रोफेशनल इंग्लिश", "दिन 29: सार्वजनिक मंच और प्रेजेंटेशन", "सबके सामने बोलने के शुरुआती व अंतिम वाक्य।", listOf("Good morning everyone, thank you for your time." to "सभी को सुप्रभात, आपके समय के लिए धन्यवाद।", "Today I would like to share three key points." to "आज मैं तीन मुख्य बिंदु साझा करना चाहूँगा।", "Thank you for listening, I welcome your questions." to "सुनने के लिए धन्यवाद, आपके सवालों का स्वागत है।"), "तेज़ बोलने के बजाय धीरे और ठहराव के साथ बोलने से आवाज असरदार लगती है।"),
        DayCourseLesson(30, "सप्ताह 4: जॉब और प्रोफेशनल इंग्लिश", "दिन 30: ग्रैंड रिव्यू व भविष्य का संकल्प", "30 दिन पूरे! अब धाराप्रवाह बोलने का नया सफर।", listOf("I have completed the thirty-day English foundation." to "मैंने तीस दिन की अंग्रेज़ी नींव पूरी कर ली है।", "English is no longer a barrier for my dreams." to "अंग्रेज़ी अब मेरे सपनों के आड़े नहीं आती।", "I will keep reading and speaking every day." to "मैं हर दिन पढ़ना और बोलना जारी रखूँगा।"), "बधाई हो! रोज़ 10 मिनट इस ऐप की कहानियाँ और Flashcards दोहराते रहें।")
    )

    val sentencePuzzles = listOf(
        SentencePuzzleItem("sp1", "मैं रोज़ स्कूल जाता हूँ।", listOf("I", "go", "to", "school", "daily"), "पहले कर्ता (I) फिर क्रिया (go) लगाएं।"),
        SentencePuzzleItem("sp2", "मुझे अभी भूख लगी है।", listOf("I", "am", "hungry", "right", "now"), "'I have hunger' नहीं — 'I am hungry' चुनें।"),
        SentencePuzzleItem("sp3", "कल वह घर नहीं आया।", listOf("He", "did", "not", "come", "yesterday"), "'did not' के साथ V1 (come) आएगा।"),
        SentencePuzzleItem("sp4", "क्या आप मेरी मदद कर सकते हैं?", listOf("Could", "you", "please", "help", "me"), "विनम्र सवाल 'Could you please...' से शुरू होता है।"),
        SentencePuzzleItem("sp5", "मेरी परीक्षा सोमवार को है।", listOf("My", "exam", "is", "on", "Monday"), "दिन से पहले हमेशा 'on' लगता है।"),
        SentencePuzzleItem("sp6", "मैं आपसे पूरी तरह सहमत हूँ।", listOf("I", "completely", "agree", "with", "you"), "'Agree' के साथ 'am' नहीं लगता।"),
        SentencePuzzleItem("sp7", "कृपया पंखा चला दीजिए।", listOf("Please", "turn", "on", "the", "fan"), "पंखा चलाने के लिए 'turn on' लगता है, 'open' नहीं।"),
        SentencePuzzleItem("sp8", "वह अभी एक किताब पढ़ रही है।", listOf("She", "is", "reading", "a", "book"), "चल रहे काम के लिए 'is + verb-ing' आता है।"),
        SentencePuzzleItem("sp9", "मैंने अपना काम पूरा कर लिया है।", listOf("I", "have", "completed", "my", "work"), "पूरा हुए काम के लिए 'have + V3' लगता है।"),
        SentencePuzzleItem("sp10", "हमें रोज़ अभ्यास करना चाहिए।", listOf("We", "should", "practice", "every", "day"), "सलाह देने के लिए 'should + V1' आता है।"),
        SentencePuzzleItem("sp11", "बोलने में कभी मत झिझको।", listOf("Never", "hesitate", "to", "speak", "English"), "मना करने की सलाह 'Never + V1' से शुरू करें।"),
        SentencePuzzleItem("sp12", "यह पहले से बहुत बेहतर है।", listOf("This", "is", "much", "better", "now"), "'more better' गलत है — 'much better' सही है।"),
        SentencePuzzleItem("sp13", "मेरा नाम राहुल है।", listOf("My", "name", "is", "Rahul", "Sharma"), "'Myself Rahul' नहीं, 'My name is' बोलें।"),
        SentencePuzzleItem("sp14", "मैं सुबह छह बजे उठता हूँ।", listOf("I", "wake", "up", "at", "six"), "घड़ी के समय से पहले 'at' आता है।"),
        SentencePuzzleItem("sp15", "कृपया थोड़ा धीरे बोलें।", listOf("Please", "speak", "a", "little", "slowly"), "अनुरोध 'Please + V1' से शुरू करें।"),
        SentencePuzzleItem("sp16", "क्या आपके पास एक पेन है?", listOf("Do", "you", "have", "a", "pen"), "सवाल पूछने के लिए 'Do you have' लगता है।"),
        SentencePuzzleItem("sp17", "बाहर तेज़ बारिश हो रही है।", listOf("It", "is", "raining", "heavily", "outside"), "मौसम बताने के लिए वाक्य 'It' से शुरू होता है।"),
        SentencePuzzleItem("sp18", "वह मेरी बड़ी बहन है।", listOf("She", "is", "my", "elder", "sister"), "सगे भाई-बहन के लिए 'elder' आता है।"),
        SentencePuzzleItem("sp19", "मुझे सुबह से सिरदर्द हो रहा है।", listOf("I", "have", "headache", "since", "morning"), "निश्चित समय के लिए 'since morning' आता है।"),
        SentencePuzzleItem("sp20", "ट्रेन सात बजे आएगी।", listOf("The", "train", "will", "arrive", "soon"), "भविष्य के लिए 'will + V1' लगाएं।"),
        SentencePuzzleItem("sp21", "यहाँ से स्टेशन कितनी दूर है?", listOf("How", "far", "is", "the", "station"), "दूरी पूछने के लिए 'How far' आता है।"),
        SentencePuzzleItem("sp22", "वह मेरा चचेरा भाई है।", listOf("He", "is", "my", "only", "cousin"), "'Cousin' के पीछे brother नहीं लगाते।"),
        SentencePuzzleItem("sp23", "आज मेरी तबीयत ठीक नहीं है।", listOf("I", "am", "not", "feeling", "well"), "'health is down' की जगह 'not feeling well' सही है।"),
        SentencePuzzleItem("sp24", "मैं अब English समझ सकता हूँ।", listOf("I", "can", "understand", "English", "now"), "क्षमता दिखाने के लिए 'can + V1' आता है।"),
        SentencePuzzleItem("sp25", "क्या मैं अंदर आ सकता हूँ?", listOf("May", "I", "come", "in", "please"), "अनुमति लेने के लिए 'May I...' विनम्र तरीका है।"),
        SentencePuzzleItem("sp26", "फोन को चार्जिंग पर लगा दो।", listOf("Put", "the", "phone", "on", "charging"), "आदेश या निर्देश क्रिया (Put) से शुरू होते हैं।"),
        SentencePuzzleItem("sp27", "चिंता मत करो सब ठीक होगा।", listOf("Do", "not", "worry", "about", "anything"), "मना करने के लिए 'Do not + V1' बोलें।"),
        SentencePuzzleItem("sp28", "मैंने कल एक किताब खरीदी।", listOf("I", "bought", "a", "book", "yesterday"), "कल खरीदी (Past) के लिए V2 'bought' आता है।"),
        SentencePuzzleItem("sp29", "चलो इस विषय पर बात करें।", listOf("Let", "us", "discuss", "this", "topic"), "'Discuss' के बाद 'about' नहीं लगता!"),
        SentencePuzzleItem("sp30", "वह मुझसे गुस्सा है।", listOf("He", "is", "angry", "with", "me"), "व्यक्ति से गुस्सा होने पर 'angry with' आता है।"),
        SentencePuzzleItem("sp31", "कृपया मेरे ईमेल का जवाब दें।", listOf("Please", "reply", "to", "my", "email"), "'revert back' नहीं, 'reply to' लिखें।"),
        SentencePuzzleItem("sp32", "सुबह पढ़ना रात से बेहतर है।", listOf("Morning", "is", "better", "than", "night"), "तुलना के लिए हमेशा 'than' (then नहीं) आता है।"),
        SentencePuzzleItem("sp33", "रवि को छोड़कर सब आए थे।", listOf("Everyone", "came", "except", "my", "friend"), "'को छोड़कर' के लिए 'except' आता है।"),
        SentencePuzzleItem("sp34", "मुझे आपका नाम जानना है।", listOf("May", "I", "know", "your", "name"), "'good name' बोलना गलत है।"),
        SentencePuzzleItem("sp35", "क्या आप इसे दोहरा सकते हैं?", listOf("Could", "you", "repeat", "that", "again"), "विनम्र निवेदन में 'Could you' लगाएं।"),
        SentencePuzzleItem("sp36", "मैं एक दिन की छुट्टी चाहता हूँ।", listOf("I", "need", "leave", "for", "today"), "आज की छुट्टी के लिए 'leave for today' सही है।"),
        SentencePuzzleItem("sp37", "अपनी डायरी में यह लिख लो।", listOf("Write", "this", "in", "your", "diary"), "लिखने वाली डायरी 'Diary' होती है, Dairy नहीं।"),
        SentencePuzzleItem("sp38", "कृपया लाइब्रेरी में शांत रहें।", listOf("Please", "keep", "quiet", "in", "library"), "चुप रहने के लिए 'keep quiet' आता है।"),
        SentencePuzzleItem("sp39", "मैं चार सालों से यहाँ हूँ।", listOf("I", "live", "here", "for", "years"), "अवधि बताने के लिए 'for' का उपयोग होता है।"),
        SentencePuzzleItem("sp40", "उसकी शादी पिछले साल हुई।", listOf("She", "got", "married", "last", "year"), "'married with' कभी न लगाएं।"),
        SentencePuzzleItem("sp41", "मैं पांच मिनट में वापस आता हूँ।", listOf("I", "will", "return", "very", "soon"), "'return back' गलत है — सिर्फ 'return' बोलें।"),
        SentencePuzzleItem("sp42", "कृपया दरवाज़ा धीरे बंद करें।", listOf("Close", "the", "door", "very", "gently"), "तरीका बताने के लिए 'gently' अंत में आता है।"),
        SentencePuzzleItem("sp43", "हमें समय का सम्मान करना चाहिए।", listOf("We", "must", "respect", "each", "minute"), "ज़रूरी नियम के लिए 'must + V1' लगाएं।"),
        SentencePuzzleItem("sp44", "मुझे एक गिलास पानी चाहिए।", listOf("I", "want", "a", "glass", "water"), "चाहिए के लिए 'I want' या 'I need' लगाएं।"),
        SentencePuzzleItem("sp45", "वह जल्दी ही ठीक हो जाएगा।", listOf("He", "will", "recover", "very", "fast"), "भविष्य के लिए 'will + recover' लगाएं।"),
        SentencePuzzleItem("sp46", "अपनी मेहनत से सब संभव है।", listOf("Everything", "is", "possible", "with", "effort"), "'Everything' के साथ एकवचन 'is' आता है।"),
        SentencePuzzleItem("sp47", "मैं पहले English से डरता था।", listOf("I", "used", "to", "fear", "English"), "पुरानी आदत बताने के लिए 'used to' आता है।"),
        SentencePuzzleItem("sp48", "आपकी मदद के लिए बहुत धन्यवाद।", listOf("Thank", "you", "for", "your", "help"), "धन्यवाद के साथ हमेशा 'for' आता है।"),
        SentencePuzzleItem("sp49", "अभ्यास से इंसान निपुण बनता है।", listOf("Daily", "practice", "makes", "you", "perfect"), "एकवचन Subject के साथ 'makes' लगेगा।"),
        SentencePuzzleItem("sp50", "अब मुझे बोलने से डर नहीं लगता।", listOf("Now", "I", "speak", "without", "fear"), "बिना डर के = 'without fear'!")
    )

    val bossQuestions = listOf(
        BossQuestionItem("गलती सुधारें: 'कल मैं बाज़ार नहीं गया'", "Yesterday I did not ___ to the market.", listOf("went", "go", "going", "gone"), 1, "'did not' के बाद हमेशा पहला रूप (go) आता है।"),
        BossQuestionItem("सही Preposition चुनें:", "The train arrives ___ 6:30 PM.", listOf("in", "on", "at", "by"), 2, "घड़ी के सटीक समय के लिए हमेशा 'at' लगता है।"),
        BossQuestionItem("हिंदी सोच सुधारें: 'वो मेरा चचेरा भाई है'", "He is my ___.", listOf("cousin brother", "cousin", "cousin boy", "brother cousin"), 1, "'Cousin' के पीछे brother/sister नहीं लगाते।"),
        BossQuestionItem("उलझाने वाले शब्द:", "Please ___ in the library.", listOf("keep quite", "keep quiet", "stay quite", "be quit"), 1, "शांत रहने के लिए 'Quiet' आता है।"),
        BossQuestionItem("सही वाक्य चुनें:", "निम्न में से कौन-सा वाक्य 100% शुद्ध है?", listOf("I am agree with you.", "I agree with you.", "I agreeing you.", "I have agree."), 1, "'Agree' खुद क्रिया है, इसलिए 'am' नहीं लगेगा।"),
        BossQuestionItem("ईमेल शिष्टाचार:", "Please ___ to my email soon.", listOf("revert back", "reply", "answer back", "tell back"), 1, "'revert back' गलत है — 'reply' या 'get back' लिखें।"),
        BossQuestionItem("सही Verb Form (V3):", "Have you ___ your lunch yet?", listOf("eat", "ate", "eaten", "eating"), 2, "Has/Have के साथ क्रिया का तीसरा रूप (V3 = eaten) आता है।"),
        BossQuestionItem("तुलना वाला शब्द चुनें:", "Morning study is better ___ night study.", listOf("then", "than", "from", "with"), 1, "तुलना के लिए हमेशा 'than' का प्रयोग होता है।"),
        BossQuestionItem("अपना परिचय कैसे दें?", "निम्न में से सही परिचय चुनें:", listOf("Myself Aman Gupta.", "I am Aman Gupta.", "Me Aman Gupta.", "Mine name is Aman."), 1, "'Myself' से वाक्य शुरू नहीं करते — 'I am' या 'My name is' सही है।"),
        BossQuestionItem("सही दिन का Preposition:", "We have an English test ___ Friday.", listOf("in", "at", "on", "to"), 2, "दिन के नाम (Monday–Sunday) से पहले हमेशा 'on' लगता है।"),
        BossQuestionItem("Verb + About नियम:", "Let us ___ the new project.", listOf("discuss about", "discuss", "discussing about", "discussed about"), 1, "'Discuss' के बाद 'about' कभी नहीं आता।"),
        BossQuestionItem("गुस्से के लिए सही शब्द:", "Why is Mohan angry ___ me?", listOf("on", "over", "with", "for"), 2, "किसी इंसान से नाराज़ होने पर 'angry with' लगता है।"),
        BossQuestionItem("शादी के वाक्य में Preposition:", "Rohan married ___ Priya last month.", listOf("with", "to", "by", "कोई शब्द नहीं (married Priya)"), 3, "Active voice में 'married' के बाद सीधा नाम आता है, with/to नहीं।"),
        BossQuestionItem("बिजली के उपकरण चलाना:", "It is hot, please ___ the fan.", listOf("open", "start on", "turn on", "open up"), 2, "पंखा/लाइट चलाने के लिए 'turn on' और बंद करने के लिए 'turn off' आता है।"),
        BossQuestionItem("सही Verb चुनें (Daily Routine):", "She ___ English every morning.", listOf("speak", "speaks", "speaking", "spoke"), 1, "He/She/एकवचन की रोज़ की आदत में Verb के साथ 's/es' (speaks) लगता है।"),
        BossQuestionItem("सही Noun चुनें (सलाह):", "My teacher gave me useful ___.", listOf("advise", "advices", "advice", "advising"), 2, "'Advice' (c वाला) संज्ञा है और इसका plural 'advices' नहीं होता।"),
        BossQuestionItem("कॉपी-पेन की दुकान:", "I bought notebooks from the ___ shop.", listOf("stationary", "stationery", "station", "state"), 1, "लिखने के सामान वाली दुकान 'Stationery' (e) होती है।"),
        BossQuestionItem("ईमानदारी का सिद्धांत:", "Honesty is a golden ___ of life.", listOf("principal", "principle", "principals", "princely"), 1, "नियम या सिद्धांत के लिए '-le' वाला 'Principle' सही है।"),
        BossQuestionItem("समय की अवधि (Since vs For):", "I have been waiting ___ two hours.", listOf("since", "from", "for", "at"), 2, "गिनती वाले समय (2 hours, 5 days) के लिए 'for' आता है।"),
        BossQuestionItem("निश्चित समय (Since vs For):", "It has been raining ___ 5 o'clock.", listOf("for", "since", "from", "by"), 1, "निश्चित शुरुआती समय (5 बजे, सुबह, 2020) के लिए 'since' आता है।"),
        BossQuestionItem("Modal Verb नियम:", "You should ___ daily without fear.", listOf("to practice", "practicing", "practice", "practiced"), 2, "Should/Can/Must के साथ हमेशा V1 आता है, 'to' या '-ing' नहीं।"),
        BossQuestionItem("Past Simple सवाल:", "Did you ___ your homework?", listOf("completed", "completing", "complete", "completes"), 2, "'Did' के साथ सवाल में भी V1 (complete) आता है।"),
        BossQuestionItem("तुलनात्मक गलती सुधारें:", "Your handwriting is ___ than mine.", listOf("more good", "more better", "much better", "best"), 2, "'more better' गलत है — 'much better' सही है।"),
        BossQuestionItem("भविष्य में अवस्था बताना:", "Tomorrow I ___ free after 4 PM.", listOf("will free", "will be free", "am will free", "be free"), 1, "'Free' विशेषण है, इसलिए 'will be free' लगेगा।"),
        BossQuestionItem("लिखने वाली डायरी:", "Write five new words in your ___ daily.", listOf("dairy", "diary", "diaries", "dairies"), 1, "लिखने वाली कॉपी 'Diary' (i पहले) होती है।"),
        BossQuestionItem("ध्यान से सुनना:", "Please ___ to the audio carefully.", listOf("hear", "hearing", "listen", "listened"), 2, "ध्यान से सुनने के लिए हमेशा 'listen to' आता है।"),
        BossQuestionItem("भूख/प्यास बताना:", "I ___ very thirsty after the walk.", listOf("have", "am", "having", "has"), 1, "भूख/प्यास के लिए हमेशा 'I am' लगता है, 'I have' नहीं।"),
        BossQuestionItem("मुहावरा (Idiom) अर्थ:", "'Piece of cake' का सही हिंदी अर्थ क्या है?", listOf("केक खाना", "बहुत कठिन काम", "बहुत आसान काम", "जन्मदिन मनाना"), 2, "'Piece of cake' का मतलब 'बहुत ही आसान काम' होता है।"),
        BossQuestionItem("सही V3 Form चुनें:", "She has ___ a very clear letter.", listOf("write", "wrote", "written", "writing"), 2, "Has/Have के साथ Write का V3 'written' आता है।"),
        BossQuestionItem("सही V2 Form (Past):", "Last night I ___ for eight hours.", listOf("sleep", "slept", "sleeped", "sleeping"), 1, "Sleep का Past form (V2) 'slept' होता है।"),
        BossQuestionItem("उम्मीद खोना (Loose vs Lose):", "Do not ___ confidence while speaking.", listOf("loose", "lose", "lost", "loosing"), 1, "खोने या हारने के लिए एक 'o' वाला 'Lose' आता है।"),
        BossQuestionItem("रेगिस्तान की स्पेलिंग:", "Sahara is the largest ___ in the world.", listOf("dessert", "desert", "desart", "dissert"), 1, "रेगिस्तान 'Desert' (1 s) और मीठा 'Dessert' (2 s) होता है।"),
        BossQuestionItem("वापस आना (Return):", "When will you ___ from Delhi?", listOf("return back", "return", "returning back", "back return"), 1, "'Return' के पीछे 'back' कभी नहीं लगाया जाता।"),
        BossQuestionItem("शुभ नाम पूछना:", "Sir, ___?", listOf("what is your good name", "may I know your name", "your good name please", "say your good name"), 1, "English में 'May I know your name?' सबसे विनम्र और सही वाक्य है।"),
        BossQuestionItem("महीने व साल से पहले Preposition:", "My brother was born ___ 2004.", listOf("at", "on", "in", "by"), 2, "साल और महीने के नाम से पहले हमेशा 'in' लगता है।"),
        BossQuestionItem("के अलावा (Accept vs Except):", "Everyone passed ___ two careless students.", listOf("accept", "except", "expect", "aspect"), 1, "'को छोड़कर / के अलावा' के लिए 'except' सही है।"),
        BossQuestionItem("असर डालना (Affect vs Effect):", "Mobile scrolling can ___ your sleep.", listOf("effect", "affect", "affecting", "effective"), 1, "असर डालने की क्रिया (Verb) के लिए 'Affect' आता है।"),
        BossQuestionItem("पुरानी आदत (Used to):", "I ___ wake up late, but now I wake up at 6.", listOf("use to", "used to", "using to", "was used"), 1, "पुरानी आदत जो अब बदल गई हो उसके लिए 'used to' आता है।"),
        BossQuestionItem("तारीफ़ करना (Compliment):", "He gave me a warm ___ on my speech.", listOf("complement", "compliment", "complain", "complete"), 1, "तारीफ़ करने के लिए 'i' वाला 'Compliment' आता है।"),
        BossQuestionItem("देर रात तक मेहनत (Idiom):", "'Burn the midnight oil' का क्या अर्थ है?", listOf("तेल जलाना", "देर रात तक मेहनत करना", "जल्दी सो जाना", "बिना सोचे बोलना"), 1, "'Burn the midnight oil' यानी देर रात तक लगन से पढ़ाई/काम करना।")
    )

    val verbForms = listOf(
        VerbFormItem("Go", "Went", "Gone", "गो - वेंट - गॉन", "जाना"),
        VerbFormItem("Come", "Came", "Come", "कम - केम - कम", "आना"),
        VerbFormItem("Eat", "Ate", "Eaten", "ईट - एट - ईटन", "खाना"),
        VerbFormItem("Drink", "Drank", "Drunk", "ड्रिंक - ड्रैंक - ड्रंक", "पीना"),
        VerbFormItem("Speak", "Spoke", "Spoken", "स्पीक - स्पोक - स्पोकन", "बोलना"),
        VerbFormItem("Write", "Wrote", "Written", "राइट - रोट - रिटन", "लिखना"),
        VerbFormItem("Read", "Read (रेड)", "Read (रेड)", "रीड - रेड - रेड", "पढ़ना"),
        VerbFormItem("See", "Saw", "Seen", "सी - सॉ - सीन", "देखना"),
        VerbFormItem("Hear", "Heard", "Heard", "हियर - हर्ड - हर्ड", "सुनना"),
        VerbFormItem("Take", "Took", "Taken", "टेक - टुक - टेकन", "लेना"),
        VerbFormItem("Give", "Gave", "Given", "गिव - गेव - गिवन", "देना"),
        VerbFormItem("Make", "Made", "Made", "मेक - मेड - मेड", "बनाना"),
        VerbFormItem("Do", "Did", "Done", "डू - डिड - डन", "करना"),
        VerbFormItem("Know", "Knew", "Known", "नो - न्यू - नोन", "जानना"),
        VerbFormItem("Think", "Thought", "Thought", "थिंक - थॉट - थॉट", "सोचना"),
        VerbFormItem("Understand", "Understood", "Understood", "अंडरस्टैंड - अंडरस्टुड", "समझना"),
        VerbFormItem("Learn", "Learned / Learnt", "Learned / Learnt", "लर्न - लर्न्ड", "सीखना"),
        VerbFormItem("Teach", "Taught", "Taught", "टीच - टॉट - टॉट", "सिखाना / पढ़ाना"),
        VerbFormItem("Buy", "Bought", "Bought", "बाय - बॉट - बॉट", "खरीदना"),
        VerbFormItem("Sell", "Sold", "Sold", "सेल - सोल्ड - सोल्ड", "बेचना"),
        VerbFormItem("Bring", "Brought", "Brought", "ब्रिंग - ब्रॉट - ब्रॉट", "लाना"),
        VerbFormItem("Send", "Sent", "Sent", "सेंड - सेंट - सेंट", "भेजना"),
        VerbFormItem("Find", "Found", "Found", "फाइंड - फाउंड - फाउंड", "ढूँढना / पाना"),
        VerbFormItem("Keep", "Kept", "Kept", "कीप - केप्ट - केप्ट", "रखना"),
        VerbFormItem("Leave", "Left", "Left", "लीव - लेफ्ट - लेफ्ट", "छोड़ना / निकलना"),
        VerbFormItem("Meet", "Met", "Met", "मीट - मेट - मेट", "मिलना"),
        VerbFormItem("Pay", "Paid", "Paid", "पे - पेड - पेड", "भुगतान करना"),
        VerbFormItem("Run", "Ran", "Run", "रन - रैन - रन", "दौड़ना"),
        VerbFormItem("Sit", "Sat", "Sat", "सिट - सैट - सैट", "बैठना"),
        VerbFormItem("Stand", "Stood", "Stood", "स्टैंड - स्टुड - स्टुड", "खड़े होना"),
        VerbFormItem("Sleep", "Slept", "Slept", "स्लीप - स्लेप्ट - स्लेप्ट", "सोना"),
        VerbFormItem("Wake", "Woke", "Woken", "वेक - वोक - वोकन", "जागना"),
        VerbFormItem("Win", "Won", "Won", "विन - वन - वन", "जीतना"),
        VerbFormItem("Lose", "Lost", "Lost", "लूज़ - लॉस्ट - लॉस्ट", "हारना / खोना"),
        VerbFormItem("Begin", "Began", "Begun", "बिगिन - बिगेन - बिगन", "शुरू करना"),
        VerbFormItem("Break", "Broke", "Broken", "ब्रेक - ब्रोक - ब्रोकन", "तोड़ना"),
        VerbFormItem("Build", "Built", "Built", "बिल्ड - बिल्ट - बिल्ट", "निर्माण करना"),
        VerbFormItem("Choose", "Chose", "Chosen", "चूज़ - चोज़ - चोज़न", "चुनना"),
        VerbFormItem("Feel", "Felt", "Felt", "फील - फेल्ट - फेल्ट", "महसूस करना"),
        VerbFormItem("Forget", "Forgot", "Forgotten", "फॉरगेट - फॉरगॉट - फॉरगॉटन", "भूलना"),
        VerbFormItem("Forgive", "Forgave", "Forgiven", "फॉरगिव - फॉरगेव - फॉरगिवन", "माफ़ करना"),
        VerbFormItem("Grow", "Grew", "Grown", "ग्रो - ग्र्यू - ग्रोन", "बढ़ना / उगना"),
        VerbFormItem("Hold", "Held", "Held", "होल्ड - हेल्ड - हेल्ड", "पकड़ना"),
        VerbFormItem("Show", "Showed", "Shown", "शो - शोड - शोन", "दिखाना"),
        VerbFormItem("Tell", "Told", "Told", "टेल - टोल्ड - टोल्ड", "बताना"),
        VerbFormItem("Try", "Tried", "Tried", "ट्राय - ट्राइड - ट्राइड", "कोशिश करना"),
        VerbFormItem("Wait", "Waited", "Waited", "वेट - वेटेड - वेटेड", "इंतज़ार करना"),
        VerbFormItem("Work", "Worked", "Worked", "वर्क - वर्क्ड - वर्क्ड", "काम करना"),
        VerbFormItem("Ask", "Asked", "Asked", "आस्क - आस्क्ड", "पूछना"),
        VerbFormItem("Answer", "Answered", "Answered", "आंसर - आंसर्ड", "जवाब देना")
    )

    val idioms = listOf(
        IdiomItem("Piece of cake", "बहुत ही आसान काम", "With daily practice, speaking English becomes a piece of cake.", "रोज़ के अभ्यास से अंग्रेज़ी बोलना बहुत आसान काम बन जाता है।"),
        IdiomItem("Break the ice", "चुप्पी तोड़कर बातचीत शुरू करना", "A friendly smile helps break the ice in a new group.", "एक प्यारी मुस्कान नए लोगों के बीच बातचीत शुरू करने में मदद करती है।"),
        IdiomItem("Once in a blue moon", "कभी-कभार (ईद का चाँद)", "I eat fast food only once in a blue moon.", "मैं बाहर का फास्ट फूड कभी-कभार ही खाता हूँ।"),
        IdiomItem("Hit the nail on the head", "बिल्कुल सटीक बात कहना", "You hit the nail on the head with that suggestion.", "आपने उस सुझाव से बिल्कुल सटीक बात कही।"),
        IdiomItem("Better late than never", "देर आए दुरुस्त आए", "Starting to learn at any age is better late than never.", "किसी भी उम्र में सीखना शुरू करना 'देर आए दुरुस्त आए' जैसा है।"),
        IdiomItem("Keep your fingers crossed", "अच्छे नतीजे की उम्मीद रखना", "My result comes out tomorrow, keep your fingers crossed!", "कल मेरा रिज़ल्ट आने वाला है, अच्छे की दुआ करें!"),
        IdiomItem("On cloud nine", "बहुत ज़्यादा खुश होना", "She was on cloud nine after getting her first job.", "पहली नौकरी मिलने पर वह सातवें आसमान पर थी।"),
        IdiomItem("Call it a day", "आज का काम समाप्त करना", "We have practiced for an hour, let us call it a day.", "हमने एक घंटे अभ्यास कर लिया, अब आज के लिए विराम लेते हैं।"),
        IdiomItem("Under the weather", "तबीयत थोड़ी खराब लगना", "I am feeling a bit under the weather today.", "आज मेरी तबीयत थोड़ी ठीक नहीं लग रही है।"),
        IdiomItem("Blessing in disguise", "दुःख में छिपा अच्छा अवसर", "Losing that small chance was a blessing in disguise.", "उस छोटे मौके का छूटना बाद में बहुत अच्छा साबित हुआ।"),
        IdiomItem("Burn the midnight oil", "देर रात तक मेहनत करना", "Students burn the midnight oil before board exams.", "बोर्ड परीक्षाओं से पहले छात्र देर रात तक मेहनत करते हैं।"),
        IdiomItem("Actions speak louder than words", "कथनी से ज़्यादा करनी बोलती है", "Show your progress through work — actions speak louder than words.", "काम से अपनी तरक्की दिखाएं — कथनी से ज़्यादा करनी बोलती है।")
    )

    val dailyUseSentences = listOf(
        DailyUseSentence("घर और परिवार", "Please wake me up early tomorrow.", "प्लीज़ वेक मी अप अर्ली टुमॉरो", "कृपया मुझे कल जल्दी जगा देना।"),
        DailyUseSentence("घर और परिवार", "I am getting ready for college.", "आय एम गेटिंग रेडी फॉर कॉलेज", "मैं कॉलेज के लिए तैयार हो रहा हूँ।"),
        DailyUseSentence("घर और परिवार", "Put the phone on charging.", "पुट द फोन ऑन चार्जिंग", "फोन को चार्जिंग पर लगा दो।"),
        DailyUseSentence("घर और परिवार", "Don't worry, everything will be fine.", "डोंट वरी, एवरीथिंग विल बी फाइन", "चिंता मत करो, सब ठीक हो जाएगा।"),
        DailyUseSentence("घर और परिवार", "Have you had your breakfast?", "हैव यू हैड योर ब्रेकफास्ट", "क्या आपने नाश्ता कर लिया?"),
        DailyUseSentence("घर और परिवार", "Speak softly, grandfather is sleeping.", "स्पीक सॉफ्टली, ग्रैंडफादर इज़ स्लीपिंग", "धीरे बोलो, दादाजी सो रहे हैं।"),
        DailyUseSentence("घर और परिवार", "Close the door gently when you leave.", "क्लोज़ द डोर जेंटली व्हेन यू लीव", "जाते समय दरवाज़ा धीरे से बंद करना।"),
        DailyUseSentence("घर और परिवार", "Is lunch ready yet? I am hungry.", "इज़ लंच रेडी येट? आय एम हंग्री", "क्या दोपहर का खाना तैयार है? मुझे भूख लगी है।"),

        DailyUseSentence("पढ़ाई और ऑफिस", "Could you explain this topic once more?", "कुड यू एक्सप्लेन दिस टॉपिक वन्स मोर", "क्या आप यह विषय एक बार फिर समझा सकते हैं?"),
        DailyUseSentence("पढ़ाई और ऑफिस", "I need a little more time to finish.", "आय नीड अ लिटिल मोर टाइम टू फिनिश", "मुझे पूरा करने के लिए थोड़ा और समय चाहिए।"),
        DailyUseSentence("पढ़ाई और ऑफिस", "Let me note this down in my diary.", "लेट मी नोट दिस डाउन इन माय डायरी", "मुझे यह अपनी डायरी में लिख लेने दीजिए।"),
        DailyUseSentence("पढ़ाई और ऑफिस", "I am working hard to improve my skills.", "आय एम वर्किंग हार्ड टू इम्प्रूव माय स्किल्स", "मैं अपने कौशल सुधारने के लिए मेहनत कर रहा हूँ।"),
        DailyUseSentence("पढ़ाई और ऑफिस", "Please send me the PDF on WhatsApp.", "प्लीज़ सेंड मी द पीडीएफ ऑन व्हाट्सएप", "कृपया मुझे व्हाट्सएप पर पीडीएफ भेज दीजिए।"),
        DailyUseSentence("पढ़ाई और ऑफिस", "Sorry for the mistake, I will fix it.", "सॉरी फॉर द मिस्टेक, आय विल फिक्स इट", "गलती के लिए क्षमा, मैं इसे ठीक कर दूँगा।"),
        DailyUseSentence("पढ़ाई और ऑफिस", "Please check the attached file.", "प्लीज़ चेक द अटैच्ड फाइल", "कृपया संलग्न फ़ाइल की जाँच कर लें।"),
        DailyUseSentence("पढ़ाई और ऑफिस", "When is the deadline for this task?", "व्हेन इज़ द डेडलाइन फॉर दिस टास्क", "इस काम को पूरा करने की अंतिम तारीख कब है?"),

        DailyUseSentence("बाज़ार और यात्रा", "How far is the railway station from here?", "हाउ फार इज़ द रेलवे स्टेशन फ्रॉम हियर", "यहाँ से रेलवे स्टेशन कितनी दूर है?"),
        DailyUseSentence("बाज़ार और यात्रा", "Please give me change for five hundred.", "प्लीज़ गिव मी चेंज फॉर फाइव हंड्रेड", "कृपया मुझे पाँच सौ के छुट्टे दे दीजिए।"),
        DailyUseSentence("बाज़ार और यात्रा", "Is there any discount on this item?", "इज़ देयर एनी डिस्काउंट ऑन दिस आइटम", "क्या इस सामान पर कोई छूट है?"),
        DailyUseSentence("बाज़ार और यात्रा", "Wait here, I will be back in five minutes.", "वेट हियर, आय विल बी बैक इन फाइव मिनट्स", "यहीं रुकिए, मैं पाँच मिनट में वापस आता हूँ।"),
        DailyUseSentence("बाज़ार और यात्रा", "Please stop the auto near the main gate.", "प्लीज़ स्टॉप द ऑटो नियर द मेन गेट", "कृपया मुख्य द्वार के पास ऑटो रोक दें।"),
        DailyUseSentence("बाज़ार और यात्रा", "Which way leads to the bus stand?", "व्हिच वे लीड्स टू द बस स्टैंड", "बस स्टैंड जाने का रास्ता कौन-सा है?"),

        DailyUseSentence("विनम्रता और मदद", "Excuse me, could you help me for a moment?", "एक्सक्यूज़ मी, कुड यू हेल्प मी फॉर अ मोमेंट", "क्षमा करें, क्या आप एक पल मेरी मदद कर सकते हैं?"),
        DailyUseSentence("विनम्रता और मदद", "That is very kind of you.", "दैट इज़ वेरी काइंड ऑफ़ यू", "आपकी बहुत कृपा है।"),
        DailyUseSentence("विनम्रता और मदद", "No problem at all, you are welcome.", "नो प्रॉब्लम एट ऑल, यू आर वेलकम", "कोई बात नहीं, आपका स्वागत है।"),
        DailyUseSentence("विनम्रता और मदद", "Please speak a little slowly, I am learning.", "प्लीज़ स्पीक अ लिटिल स्लोली, आय एम लर्निंग", "कृपया थोड़ा धीरे बोलें, मैं अभी सीख रहा हूँ।"),
        DailyUseSentence("विनम्रता और मदद", "I really appreciate your support.", "आय रियली अप्रिशिएट योर सपोर्ट", "मैं आपके सहयोग की सचमुच कद्र करता हूँ।"),
        DailyUseSentence("विनम्रता और मदद", "Sorry to bother you, may I ask one question?", "सॉरी टू बॉदर यू, मे आय आस्क वन क्वेश्चन", "परेशान करने के लिए क्षमा, क्या मैं एक सवाल पूछ सकता हूँ?")
    )

    val roleplayScenarios = listOf(
        RoleplayScenario("interview", "जॉब इंटरव्यू (Job Interview)", "HR आपसे आपका परिचय, ताकत और जॉइनिंग पूछ रहे हैं।", "Hello! Tell me a little about yourself and your strengths.", "नमस्ते! मुझे अपने बारे में और अपनी खूबियों के बारे में थोड़ा बताइए।", "हिंदी हिंट: पहले अपना नाम, पढ़ाई और अपनी 1 अच्छी आदत (जैसे quick learner) बोलें।", listOf(
            RoleplayTurn("HR", "That sounds good. Why do you want to join our company?", "अच्छा लगा। आप हमारी कंपनी में क्यों जुड़ना चाहते हैं?", "बोलें: मुझे सीखने का शौक है और आपकी कंपनी में काम करके मैं अपने कौशल बढ़ा सकता हूँ।", listOf("I want to learn new skills and grow with your team.", "Your company has a great work culture, and I am eager to contribute.")),
            RoleplayTurn("HR", "Great! When can you start if selected?", "बहुत बढ़िया! अगर आपका चयन होता है तो आप कब से शुरू कर सकते हैं?", "बोलें: मैं तुरंत या अगले सोमवार से जुड़ सकता हूँ।", listOf("I can join immediately from next Monday.", "I am available to start as soon as required."))
        )),
        RoleplayScenario("shop", "दुकान या बाज़ार में बातचीत", "आप एक किताब या मोबाइल कवर खरीदने गए हैं।", "Good morning! How can I help you today?", "सुप्रभात! आज मैं आपकी क्या मदद कर सकता हूँ?", "हिंदी हिंट: बोलें कि आप एक इंग्लिश ग्रामर की किताब ढूँढ रहे हैं।", listOf(
            RoleplayTurn("Shopkeeper", "Sure, we have two options. Would you like the beginner guide?", "जी हाँ, हमारे पास दो विकल्प हैं। क्या आपको शुरुआती गाइड चाहिए?", "बोलें: जी हाँ, कृपया कीमत बता दीजिए।", listOf("Yes please. How much does it cost?", "Can you show me the simple one and tell me the price?"))
        )),
        RoleplayScenario("doctor", "डॉक्टर के पास (Doctor Visit)", "आपको कल से हल्का बुखार और गले में खराश है।", "Hello, please sit down. What problem are you facing?", "नमस्ते, बैठिए। आपको क्या तकलीफ़ हो रही है?", "हिंदी हिंट: बोलें 'I have had a mild fever since yesterday.'", listOf(
            RoleplayTurn("Doctor", "Okay, let me check. Are you taking any medicine right now?", "ठीक है, मैं जाँच करता हूँ। क्या आप अभी कोई दवाई ले रहे हैं?", "बोलें: नहीं डॉक्टर, मैंने अभी कोई दवाई नहीं ली है।", listOf("No doctor, I have not taken any medicine yet.", "Only warm water, no medicine so far."))
        )),
        RoleplayScenario("phone", "फोन कॉल (Customer Care / Office)", "आपको कस्टमर केयर या ऑफिस कॉल पर विनम्र बात करनी है।", "Good afternoon, thank you for calling support. How may I assist you?", "नमस्कार, सपोर्ट पर कॉल करने के लिए धन्यवाद। मैं आपकी क्या सहायता कर सकता हूँ?", "हिंदी हिंट: बोलें कि आपका ऑर्डर या फॉर्म अभी तक अपडेट नहीं हुआ है।", listOf(
            RoleplayTurn("Agent", "I understand. Could you please share your registered mobile number?", "मैं समझता हूँ। क्या आप कृपया अपना रजिस्टर्ड मोबाइल नंबर बता सकते हैं?", "बोलें: जी ज़रूर, कृपया नोट कीजिए।", listOf("Yes sure, please note down my number.", "Of course, thank you for checking this quickly."))
        )),
        RoleplayScenario("travel", "रेलवे स्टेशन व यात्रा पूछताछ", "आप टिकट काउंटर पर ट्रेन और प्लेटफॉर्म के बारे में पूछ रहे हैं।", "Next please! Where would you like to travel today?", "अगले आइए! आज आप कहाँ की यात्रा करना चाहेंगे?", "हिंदी हिंट: बोलें 'One ticket to Jaipur, please.'", listOf(
            RoleplayTurn("Clerk", "The train departs at 4:15 PM from platform number three.", "ट्रेन शाम 4:15 बजे प्लेटफॉर्म नंबर तीन से छूटेगी।", "बोलें: धन्यवाद, क्या यह समय पर है?", listOf("Thank you! Is the train running on time today?", "Thanks a lot for the information."))
        ))
    )

    val pronunciationDrills = listOf(
        PronunciationDrill("p1", "S / School ट्रिक (इस्कूल न बोलें)", "I go to school every morning at seven.", "आय गो टू स्कूल एवरी मॉर्निंग एट सेवेन", "मैं रोज़ सुबह सात बजे स्कूल जाता हूँ।", "'School' को 'इस्कूल' न बोलें — 'स्-कूल' की हल्की आवाज़ से शुरू करें।"),
        PronunciationDrill("p2", "V और W का फर्क", "We visited a very wonderful village.", "वी विज़िटेड अ वेरी वंडरफुल विलेज", "हमने एक बहुत ही सुंदर गाँव की सैर की।", "V (Very) में ऊपर के दाँत नीचे के होंठ को छुएंगे; W (We) में होंठ गोल होंगे।"),
        PronunciationDrill("p3", "Sh बनाम S का अंतर", "She sells fresh shoes in the shop.", "शी सेल्स फ्रेश शूज़ इन द शॉप", "वह दुकान में नए जूते बेचती है।", "'She' और 'Shoes' में साफ 'श' बोलें, 'सी' या 'सूज़' नहीं।"),
        PronunciationDrill("p4", "Silent Letters (अक्षर जो नहीं बोले जाते)", "An honest person walks calmly with knowledge.", "एन ऑनेस्ट पर्सन वॉक्स कामली विद नॉलेज", "एक ईमानदार व्यक्ति ज्ञान के साथ शांति से चलता है।", "Honest में H, Walk में L, Calm में L और Knowledge में K साइलेंट रहता है।"),
        PronunciationDrill("p5", "Z बनाम J (ज़ू बनाम जू)", "Zero zebras jumped inside the zoo.", "ज़ीरो ज़ेब्राज़ जम्प्ड इनसाइड द ज़ू", "चिड़ियाघर के अंदर शून्य ज़ेब्रा कूदे।", "Zoo और Zero में 'ज़' (नीचे बिंदी वाला) बोलें, 'जू/जीरो' नहीं।"),
        PronunciationDrill("p6", "F बनाम P/Ph", "Five friendly farmers found fresh fruits.", "फाइव फ्रेंडली फार्मर्स फाउंड फ्रेश फ्रूट्स", "पाँच मिलनसार किसानों को ताज़े फल मिले।", "F बोलते समय दोनों होंठ पूरी तरह बंद न करें — हल्की हवा बाहर आने दें।"),
        PronunciationDrill("p7", "Th की सही आवाज़ (द/थ)", "They thanked their mother for this gift.", "दे थैंक्ड देयर मदर फॉर दिस गिफ्ट", "उन्होंने इस उपहार के लिए अपनी माँ को धन्यवाद दिया।", "Thank में 'थ' और They/This में हल्का 'द' बोलें।"),
        PronunciationDrill("p8", "आत्मविश्वास वाला परिचय", "I am practicing English daily to speak fluently.", "आय एम प्रैक्टिसिंग इंग्लिश डेली टू स्पीक फ्लुएंटली", "मैं धाराप्रवाह बोलने के लिए रोज़ अंग्रेज़ी का अभ्यास कर रहा हूँ।", "'Fluently' को तोड़कर साफ बोलें: फ्लू-एंट-ली।")
    )

    val bridgeTraps = listOf(
        BridgeTrap("b1", "मुझे भूख लगी है।", "I have hunger. / Hunger is coming to me.", "I am hungry.", "अवस्था बताने के लिए 'I am + hungry/thirsty/sleepy' आता है।"),
        BridgeTrap("b2", "मैं आपसे सहमत हूँ।", "I am agree with you.", "I agree with you.", "'Agree' खुद एक क्रिया (Verb) है — इसके साथ 'am/is/are' नहीं लगता।"),
        BridgeTrap("b3", "वो मेरा चचेरा भाई है।", "He is my cousin brother.", "He is my cousin.", "'Cousin' में भाई/बहन का अर्थ पहले से जुड़ा है, पीछे brother/sister गलत है।"),
        BridgeTrap("b4", "कृपया वापस जवाब दें।", "Please revert back to me.", "Please reply to me. / Please get back to me.", "'Revert' का मतलब ही वापस आना है, इसलिए 'back' नहीं लगाते।"),
        BridgeTrap("b5", "आज मेरी तबीयत ठीक नहीं है।", "Today my health is down.", "I am not feeling well today.", "तबीयत के लिए 'not feeling well' सबसे प्राकृतिक English है।"),
        BridgeTrap("b6", "आपका शुभ नाम क्या है?", "What is your good name?", "May I know your name, please?", "'Good name' हिंदी का सीधा अनुवाद है, सही English में 'your name' बोलें।"),
        BridgeTrap("b7", "मेरा सर घूम रहा है।", "My head is rotating.", "I feel dizzy.", "चक्कर आने या सिर घूमने के लिए 'I feel dizzy' बोला जाता है।"),
        BridgeTrap("b8", "उसकी शादी राहुल से हुई।", "She married with Rahul.", "She married Rahul. / She got married to Rahul.", "'Marry' के बाद सीधा नाम आता है, 'with' नहीं लगता।"),
        BridgeTrap("b9", "चलो इस पर चर्चा करते हैं।", "Let's discuss about this.", "Let's discuss this.", "'Discuss' के बाद 'about' कभी नहीं लगाया जाता।"),
        BridgeTrap("b10", "मुझे ठंड लग रही है।", "Cold is hitting me.", "I am feeling cold.", "सर्दी या गर्मी लगने पर 'I am feeling cold/hot' बोलें।"),
        BridgeTrap("b11", "यह पहले से ज्यादा अच्छा है।", "This is more better than before.", "This is much better than before.", "'Better' खुद तुलनात्मक शब्द है, इसके आगे 'more' लगाना गलत है।"),
        BridgeTrap("b12", "मैं 2 बजे फ्री हो जाऊँगा।", "I will free at 2 o'clock.", "I will be free at 2 o'clock.", "'Free' एक विशेषण है, इसलिए भविष्य में 'will be free' लगेगा।"),
        BridgeTrap("b13", "वह मुझसे गुस्सा है।", "He is angry on me.", "He is angry with me.", "किसी व्यक्ति से गुस्सा होने पर 'angry with', किसी बात पर 'angry at' लगता है।"),
        BridgeTrap("b14", "कृपया पंखा चला दो।", "Please open the fan.", "Please turn on the fan.", "बिजली के उपकरणों के लिए 'open/close' नहीं, 'turn on / turn off' लगाते हैं।"),
        BridgeTrap("b15", "मेरा एग्जाम आने वाला है।", "My exam is coming on head.", "My exam is just around the corner.", "नज़दीक आने वाली चीज़ के लिए 'around the corner' या 'coming soon' बोलें।")
    )

    val sampleHinglishPresets = listOf(
        "Kal main office late jaunga kyuki traffic hai",
        "Mujhe English bolne mein dar lagta hai",
        "Kya aap mujhe chutti de sakte ho",
        "Main roz subah jaldi uthta hun",
        "Mera phone charge nahi ho raha hai",
        "Mujhe samajh nahi aaya please fir se batao"
    )

    fun convertHinglishOffline(input: String): HinglishConversion {
        val clean = input.trim().lowercase()
        return when {
            clean.contains("office") && clean.contains("late") -> HinglishConversion(input, "Tomorrow I will go to the office late because of traffic.", "I may arrive late at the office tomorrow due to heavy traffic.", "I'll be running a bit late for work tomorrow — traffic is quite heavy.", "भविष्य के लिए 'will / may' लगाएं और 'kyuki traffic hai' के लिए 'because of traffic' बोलें।")
            clean.contains("dar") || clean.contains("scared") || clean.contains("hesitat") -> HinglishConversion(input, "I feel scared to speak in English.", "I often hesitate while speaking English.", "I get a little nervous when speaking English, though I'm working on it.", "'डर लगना / झिझकना' के लिए 'feel scared' या 'hesitate' सबसे सटीक शब्द हैं।")
            clean.contains("chutti") || clean.contains("leave") -> HinglishConversion(input, "Can you give me leave today?", "Could you please grant me leave for today?", "Would it be possible for me to take the day off today?", "विनम्र निवेदन के लिए 'Can' से बेहतर 'Could you please' होता है।")
            clean.contains("subah") || clean.contains("uthta") || clean.contains("wake") -> HinglishConversion(input, "I wake up early every morning.", "I maintain a habit of waking up early every morning.", "I'm an early riser — I wake up early every day.", "रोज़ की आदत के लिए हमेशा Present Simple (I wake up) का उपयोग करें।")
            clean.contains("phone") && clean.contains("charge") -> HinglishConversion(input, "My phone is not charging.", "My mobile device is currently failing to charge.", "My phone won't charge properly — maybe the cable is loose.", "अभी हो रही समस्या के लिए 'is not + verb-ing' (is not charging) लगाएं।")
            clean.contains("samajh") || clean.contains("batao") || clean.contains("repeat") -> HinglishConversion(input, "I did not understand, please tell me again.", "Could you please explain that again? I could not follow.", "Sorry, I missed that part — could you run it by me once more?", "'नहीं आया' बीते समय की बात है, इसलिए 'I did not understand' लगेगा।")
            else -> {
                val words = clean.replace("kal", "tomorrow").replace("aaj", "today").replace("main", "I").replace("mujhe", "I").replace("tum", "you").replace("aap", "you").replace("ghar", "home").replace("kaam", "work").replace("paani", "water").replace("khana", "food").replace("chahiye", "need").replace("jaunga", "will go").replace("aaunga", "will come")
                HinglishConversion(input, "Simple: \"${words.replaceFirstChar { it.uppercase() }}\"", "Formal: Structure it clearly as Subject + Helping Verb + Main Verb + Object.", "Pro Tip: Settings में अपनी Free Gemini Key जोड़कर अनलिमिटेड Hinglish AI अनुवाद पाएं!", "वाक्य बनाने का गोल्डन नियम: पहले कर्ता (I/You/He) + फिर काम (Verb) + फिर बाकी बात (Object)।")
            }
        }
    }

    val grammarConcepts = listOf(
        GrammarConcept("g1", "Tenses", "Present Simple (रोज़ की आदत)", "Subject + V1 (s/es for He/She)", "हमेशा / रोज़ (Past ◄── [NOW] ──► Future)", "She speaks English clearly.", "वह साफ़ अंग्रेज़ी बोलती है।", "अंत में 'ता है / ती है' आए तो V1 लगाएं; He/She के साथ s/es जोड़ें।"),
        GrammarConcept("g2", "Tenses", "Present Continuous (अभी हो रहा है)", "Subject + is/am/are + V1-ing", "ठीक इसी समय (Past ─── [● NOW] ─── Future)", "I am learning new words right now.", "मैं अभी नए शब्द सीख रहा हूँ।", "जब वाक्य में 'रहा है / रही है' हो तो हमेशा is/am/are + ing लगाएं।"),
        GrammarConcept("g3", "Tenses", "Present Perfect (काम पूरा हो चुका)", "Subject + has/have + V3", "अभी-अभी पूरा हुआ (Past ──► [● NOW])", "I have finished my daily practice.", "मैंने अपना दैनिक अभ्यास पूरा कर लिया है।", "'लिया है / चुका है' के लिए has/have + V3 (done, gone, seen) आता है।"),
        GrammarConcept("g4", "Tenses", "Past Simple (बीती हुई बात)", "Subject + V2  |  did not + V1", "बीता समय ([PAST ●] ──── NOW ──── Future)", "I watched a movie, but I did not sleep late.", "मैंने फिल्म देखी, लेकिन मैं देर से नहीं सोया।", "'did not' आते ही V2 वापस V1 बन जाता है!"),
        GrammarConcept("g5", "Tenses", "Past Continuous (बीते समय चल रहा था)", "Subject + was/were + V1-ing", "बीते समय में जारी ([PAST ~~~] ── NOW)", "It was raining when I left home.", "जब मैं घर से निकला तब बारिश हो रही थी।", "एकवचन के साथ was, बहुवचन (You/We/They) के साथ were।"),
        GrammarConcept("g6", "Tenses", "Future Simple (आने वाला कल)", "Subject + will + V1", "आने वाला समय (Past ──── NOW ──── [● FUTURE])", "I will practice for 10 minutes tonight.", "मैं आज रात 10 मिनट अभ्यास करूँगा।", "'गा / गे / गी' के लिए सबके साथ बिना सोचे 'will + V1' लगा दें।"),
        GrammarConcept("g7", "Prepositions", "IN, ON, AT का पक्का नियम", "IN (महीना/साल/अंदर) · ON (दिन/तारीख/सतह) · AT (सटीक समय)", "बड़ा घेरा (IN) ➔ मध्यम (ON) ➔ सटीक बिंदु (AT)", "The exam is in July, on Monday, at 9:00 AM.", "परीक्षा जुलाई में, सोमवार को, सुबह 9 बजे है।", "साल/महीना = IN, दिन/तारीख = ON, घड़ी का समय = AT।"),
        GrammarConcept("g8", "Prepositions", "SINCE बनाम FOR (कब से?)", "SINCE (निश्चित समय) · FOR (अवधि/गिनती)", "शुरुआती बिंदु (Since) vs कुल घंटे/दिन (For)", "I have lived here since 2022 / for four years.", "मैं यहाँ 2022 से / चार सालों से रह रहा हूँ।", "सुबह/सोमवार/2022 = SINCE; 2 घंटे/5 दिन = FOR।"),
        GrammarConcept("g9", "Modals", "Can, Could, Should और Must", "Modal + V1 (हमेशा पहला रूप)", "क्षमता (Can) · विनम्रता (Could) · सलाह (Should) · ज़रूरी (Must)", "Could you help me? You should stay calm.", "क्या आप मेरी मदद कर सकते हैं? आपको शांत रहना चाहिए।", "इनके बाद कभी 'to' या '-ing' न लगाएं।"),
        GrammarConcept("g10", "Modals", "Used to (पुरानी आदत जो अब छूट गई)", "Subject + used to + V1", "पुरानी आदत (पहले करता था, अब नहीं)", "I used to fear English, now I enjoy it.", "मैं पहले अंग्रेज़ी से डरता था, अब मुझे मज़ा आता है।", "बचपन या पुरानी आदत बताने का सबसे आसान तरीका 'used to' है।")
    )

    val flashcards = listOf(
        FlashcardItem("f1", "Confident", "कॉन्फिडेंट", "आत्मविश्वासी", "Speak slowly and look confident.", "धीरे बोलें और आत्मविश्वासी दिखें।", "Daily Life"),
        FlashcardItem("f2", "Hesitate", "हेज़िटेट", "झिझकना / हिचकिचाना", "Do not hesitate to ask questions.", "सवाल पूछने में हिचकिचाएं नहीं।", "Speaking"),
        FlashcardItem("f3", "Improve", "इम्प्रूव", "सुधार करना", "Daily reading will improve your English.", "रोज़ पढ़ने से आपकी अंग्रेज़ी सुधरेगी।", "Study"),
        FlashcardItem("f4", "Opportunity", "ऑपर्च्युनिटी", "अवसर / मौका", "Every problem brings a new opportunity.", "हर समस्या एक नया अवसर लाती है।", "Career"),
        FlashcardItem("f5", "Polite", "पोलाइट", "विनम्र / सभ्य", "Always be polite with elders and colleagues.", "बड़ों और सहकर्मियों से हमेशा विनम्र रहें।", "Office"),
        FlashcardItem("f6", "Consistent", "कंसिस्टेंट", "लगातार / नियमित", "Be consistent for 30 days to see magic.", "जादू देखने के लिए 30 दिन नियमित रहें।", "Mindset"),
        FlashcardItem("f7", "Appreciate", "अप्रिशिएट", "सराहना करना / कद्र करना", "I really appreciate your timely help.", "मैं आपकी समय पर की गई मदद की सराहना करता हूँ।", "Office"),
        FlashcardItem("f8", "Clarify", "क्लैरिफ़ाय", "स्पष्ट करना", "Let me clarify my point once again.", "मुझे अपनी बात एक बार फिर स्पष्ट करने दें।", "Speaking"),
        FlashcardItem("f9", "Encourage", "एन्करेज", "हिम्मत बढ़ाना", "Good teachers always encourage weak students.", "अच्छे शिक्षक हमेशा कमजोर छात्रों की हिम्मत बढ़ाते हैं।", "Study"),
        FlashcardItem("f10", "Patience", "पेशेंस", "धैर्य / सब्र", "Learning a new skill requires patience.", "नया कौशल सीखने के लिए धैर्य चाहिए।", "Mindset"),
        FlashcardItem("f11", "Determine", "डिटरमिन", "निश्चय करना", "Hard work will determine your success.", "कड़ी मेहनत आपकी सफलता तय करेगी।", "Career"),
        FlashcardItem("f12", "Essential", "एसेंशियल", "अत्यंत आवश्यक", "Water and clean air are essential for life.", "पानी और साफ हवा जीवन के लिए अत्यंत आवश्यक हैं।", "Daily Life"),
        FlashcardItem("f13", "Focus", "फोकस", "ध्यान केंद्रित करना", "Stay focused on one chapter at a time.", "एक समय में एक अध्याय पर ध्यान केंद्रित रखें।", "Study"),
        FlashcardItem("f14", "Gratitude", "ग्रैटिट्यूड", "आभार / कृतज्ञता", "Express gratitude to those who guide you.", "जो आपका मार्गदर्शन करें उनके प्रति आभार जताएं।", "Mindset"),
        FlashcardItem("f15", "Genuine", "जेन्युइन", "असली / सच्चा", "He gave me a genuine piece of advice.", "उसने मुझे एक सच्ची सलाह दी।", "Daily Life"),
        FlashcardItem("f16", "Habit", "हैबिट", "आदत", "Reading before sleep is a great habit.", "सोने से पहले पढ़ना एक बहुत अच्छी आदत है।", "Daily Life"),
        FlashcardItem("f17", "Independent", "इंडिपेंडेंट", "आत्मनिर्भर", "Education makes every youth independent.", "शिक्षा हर युवा को आत्मनिर्भर बनाती है।", "Career"),
        FlashcardItem("f18", "Knowledge", "नॉलेज", "ज्ञान", "Practical knowledge helps in interviews.", "व्यावहारिक ज्ञान इंटरव्यू में काम आता है।", "Study"),
        FlashcardItem("f19", "Motivate", "मोटिवेट", "प्रेरित करना", "Small wins motivate us to keep going.", "छोटी जीतें हमें आगे बढ़ते रहने को प्रेरित करती हैं।", "Mindset"),
        FlashcardItem("f20", "Observe", "ऑब्ज़र्व", "ध्यान से देखना", "Observe how fluent speakers pause.", "ध्यान से देखें कि धाराप्रवाह बोलने वाले कहाँ रुकते हैं।", "Speaking"),
        FlashcardItem("f21", "Prepare", "प्रिपेयर", "तैयारी करना", "Prepare two questions before your interview.", "इंटरव्यू से पहले दो सवाल तैयार रखें।", "Career"),
        FlashcardItem("f22", "Reliable", "रिलायबल", "भरोसेमंद", "Be a reliable friend and teammate.", "एक भरोसेमंद दोस्त और साथी बनें।", "Office"),
        FlashcardItem("f23", "Solution", "सॉल्यूशन", "समाधान / हल", "Focus on the solution, not the problem.", "समस्या पर नहीं, समाधान पर ध्यान दें।", "Office"),
        FlashcardItem("f24", "Translate", "ट्रांसलेट", "अनुवाद करना", "Try not to translate word by word.", "शब्द-दर-शब्द अनुवाद न करने की कोशिश करें।", "Speaking"),
        FlashcardItem("f25", "Urgent", "अर्जेंट", "तुरंत / ज़रूरी", "I need to make an urgent phone call.", "मुझे एक ज़रूरी फोन कॉल करना है।", "Daily Life"),
        FlashcardItem("f26", "Valuable", "वैल्यूएबल", "कीमती / उपयोगी", "Thank you for sharing valuable tips.", "उपयोगी टिप्स साझा करने के लिए धन्यवाद।", "Office"),
        FlashcardItem("f27", "Witness", "विटनेस", "गवाह / देखना", "You will witness progress in thirty days.", "तीस दिनों में आप अपनी प्रगति खुद देखेंगे।", "Mindset"),
        FlashcardItem("f28", "Achieve", "अचीव", "हासिल करना", "You can achieve anything with daily effort.", "रोज़ की मेहनत से आप कुछ भी हासिल कर सकते हैं।", "Mindset"),
        FlashcardItem("f29", "Barrier", "बैरियर", "रुकावट / बाधा", "Language should never be a barrier.", "भाषा कभी भी रुकावट नहीं बननी चाहिए।", "Speaking"),
        FlashcardItem("f30", "Fluent", "फ्लुएंट", "धाराप्रवाह", "Speaking daily makes you fluent.", "रोज़ बोलने से आप धाराप्रवाह बनते हैं।", "Speaking"),
        FlashcardItem("f31", "Courage", "करेज", "साहस / हिम्मत", "It takes courage to speak in front of others.", "दूसरों के सामने बोलने के लिए हिम्मत चाहिए।", "Mindset"),
        FlashcardItem("f32", "Schedule", "शेड्यूल", "समय-सारणी", "Make a fixed study schedule for the morning.", "सुबह के लिए एक पक्की समय-सारणी बनाएं।", "Study"),
        FlashcardItem("f33", "Punctual", "पंक्चुअल", "समय का पाबंद", "Being punctual shows respect for others.", "समय का पाबंद होना दूसरों का सम्मान दिखाता है।", "Office"),
        FlashcardItem("f34", "Dedication", "डेडिकेशन", "समर्पण / लगन", "Success comes from daily dedication.", "सफलता रोज़ की लगन से आती है।", "Career"),
        FlashcardItem("f35", "Enthusiasm", "एन्थूज़ियाज़्म", "उत्साह / जोश", "Greet your team with enthusiasm.", "अपनी टीम का उत्साह से स्वागत करें।", "Office"),
        FlashcardItem("f36", "Generous", "जेनरस", "उदार / दयालु", "He is generous with his time and advice.", "वह अपना समय और सलाह देने में उदार हैं।", "Daily Life"),
        FlashcardItem("f37", "Humble", "हम्बल", "विनम्र / सादा", "Stay humble even after big success.", "बड़ी सफलता के बाद भी विनम्र रहें।", "Mindset"),
        FlashcardItem("f38", "Negotiate", "निगोशिएट", "बातचीत से तय करना", "Learn to negotiate politely in the market.", "बाज़ार में विनम्रता से मोलभाव करना सीखें।", "Daily Life"),
        FlashcardItem("f39", "Obstacle", "ऑब्सटेकल", "अड़चन / बाधा", "Every obstacle teaches a useful lesson.", "हर अड़चन एक उपयोगी सबक सिखाती है।", "Mindset"),
        FlashcardItem("f40", "Priority", "प्रायोरिटी", "प्राथमिकता", "Keep your health and learning a top priority.", "अपनी सेहत और पढ़ाई को सबसे पहली प्राथमिकता रखें।", "Daily Life")
    )

    val confusingQuizzes = listOf(
        ConfusingWordQuiz("q1", "Accept", "स्वीकार करना", "Except", "के अलावा / को छोड़कर", "Everyone came to class ___ Ravi.", "Except", "यहाँ अर्थ है 'रवि को छोड़कर सब क्लास में आए', इसलिए 'Except' सही है।"),
        ConfusingWordQuiz("q2", "Principal", "प्रधानाचार्य / मुख्य", "Principle", "सिद्धांत / नियम", "Honesty is an important ___ in life.", "Principle", "ईमानदारी जीवन का एक 'सिद्धांत' है, इसलिए अंत में '-le' वाला Principle आएगा।"),
        ConfusingWordQuiz("q3", "Affect", "असर डालना (Verb)", "Effect", "परिणाम / असर (Noun)", "Lack of sleep can ___ your focus.", "Affect", "यहाँ 'असर डालना' एक काम (Verb) है, इसलिए 'Affect' सही होगा।"),
        ConfusingWordQuiz("q4", "Advice", "सलाह (Noun)", "Advise", "सलाह देना (Verb)", "My teacher gave me good ___.", "Advice", "'अच्छी सलाह दी' में सलाह एक संज्ञा (Noun) है — इसमें 'c' वाला Advice आता है।"),
        ConfusingWordQuiz("q5", "Loose", "ढीला", "Lose", "खो देना / हारना", "Do not ___ hope if you make a mistake.", "Lose", "यहाँ 'उम्मीद खोना' अर्थ है, इसलिए एक 'o' वाला 'Lose' सही है।"),
        ConfusingWordQuiz("q6", "Quiet", "शांत / चुप", "Quite", "काफ़ी / बिल्कुल", "Please keep ___ in the library.", "Quiet", "पुस्तकालय में 'शांत' रहने के लिए 'Quiet' आता है।"),
        ConfusingWordQuiz("q7", "Then", "फिर / तब", "Than", "से (तुलना में)", "Morning study is better ___ late night cramming.", "Than", "तुलना (better) करने के लिए हमेशा 'Than' लगता है।"),
        ConfusingWordQuiz("q8", "Weather", "मौसम", "Whether", "चाहे / या", "The ___ is very pleasant today.", "Weather", "'मौसम' के लिए 'Weather' और विकल्प के लिए 'Whether' होता है।"),
        ConfusingWordQuiz("q9", "Stationary", "रुका हुआ / स्थिर", "Stationery", "कॉपी-पेन का सामान", "I bought two pens from the ___ shop.", "Stationery", "पेन/कॉपी की दुकान के लिए 'e' वाला Stationery आता है।"),
        ConfusingWordQuiz("q10", "Dairy", "दूध की डेयरी", "Diary", "लिखने वाली डायरी", "Write five new words in your ___ daily.", "Diary", "लिखने वाली डायरी में 'i' पहले आता है (Diary)।"),
        ConfusingWordQuiz("q11", "Hear", "आवाज़ कान में पड़ना", "Listen", "ध्यान से सुनना", "Please ___ to the audio carefully.", "Listen", "ध्यान लगाकर सुनने के लिए हमेशा 'Listen to' आता है।"),
        ConfusingWordQuiz("q12", "Desert", "रेगिस्तान", "Dessert", "खाने के बाद मीठा", "Thar is a famous ___ in Rajasthan.", "Desert", "रेगिस्तान में एक 's' (Desert) और मीठे में दो 's' (Dessert) होते हैं।"),
        ConfusingWordQuiz("q13", "Compliment", "तारीफ़ करना", "Complement", "पूरा करना (पूरक)", "She gave me a nice ___ on my speech.", "Compliment", "तारीफ़ के लिए 'i' वाला Compliment आता है।"),
        ConfusingWordQuiz("q14", "Check", "जाँचना", "Cheque", "बैंक का चेक", "Please ___ your answers twice.", "Check", "जाँचने के लिए 'Check' और बैंक वाले के लिए 'Cheque' आता है।")
    )

    val stories = listOf(
        StoryItem("s1", "Aarav's First Stage Speech", "आरव का मंच पर पहला भाषण", "Day 1–7 · Beginner", listOf(
            "Aarav studied in a Hindi medium school in a small town. He always felt nervous when someone spoke English quickly.",
            "One day, his teacher encouraged him to prepare a short speech. Aarav practiced five sentences in front of a mirror every morning.",
            "On Friday, Aarav stood on the stage with courage. He spoke slowly and clearly, and the entire hall clapped with pride."
        ), "एक छोटे शहर के हिंदी माध्यम के छात्र आरव ने रोज़ आईने के सामने 5 वाक्यों का अभ्यास करके मंच का डर जीत लिया।", mapOf(
            "nervous" to GlossaryWord("Nervous", "नर्वस", "घबराया हुआ", "Adjective", "Don't feel nervous before an exam."),
            "encouraged" to GlossaryWord("Encouraged", "एन्करेज्ड", "प्रोत्साहित किया", "Verb (Past)", "My parents encouraged me to try again."),
            "prepare" to GlossaryWord("Prepare", "प्रिपेयर", "तैयारी करना", "Verb", "Let's prepare for tomorrow's test."),
            "mirror" to GlossaryWord("Mirror", "मिरर", "आईना / शीशा", "Noun", "Practice speaking in front of a mirror."),
            "courage" to GlossaryWord("Courage", "करेज", "साहस / हिम्मत", "Noun", "It takes courage to speak a new language."),
            "entire" to GlossaryWord("Entire", "एन्टायर", "पूरा / सारा", "Adjective", "The entire class listened quietly."),
            "pride" to GlossaryWord("Pride", "प्राइड", "गर्व", "Noun", "She smiled with pride.")
        )),
        StoryItem("s2", "The Smart Interview Preparation", "समझदारी भरी इंटरव्यू तैयारी", "Day 24–30 · Job-Ready", listOf(
            "Priya wanted an office job, so she improved her vocabulary and stopped translating every word from Hindi.",
            "During the interview, she was polite and honest. Whenever she needed a second to think, she took a calm breath instead of panicking.",
            "The manager appreciated her clarity and confidence, and offered her the role on the same afternoon."
        ), "प्रिया ने शब्द-दर-शब्द हिंदी अनुवाद छोड़ने और शांत रहकर बोलने की कला से अपना पहला ऑफिस इंटरव्यू पास किया।", mapOf(
            "improved" to GlossaryWord("Improved", "इम्प्रूव्ड", "बेहतर बनाया", "Verb (Past)", "He improved his handwriting."),
            "vocabulary" to GlossaryWord("Vocabulary", "वोकैबुलरी", "शब्दावली", "Noun", "Build vocabulary by learning 5 words daily."),
            "translating" to GlossaryWord("Translating", "ट्रांसलेटिंग", "अनुवाद करना", "Verb (-ing)", "Stop translating word-by-word."),
            "polite" to GlossaryWord("Polite", "पोलाइट", "विनम्र", "Adjective", "Be polite when speaking to seniors."),
            "calm" to GlossaryWord("Calm", "काम (L साइलेंट)", "शांत", "Adjective", "Stay calm and speak clearly."),
            "appreciated" to GlossaryWord("Appreciated", "अप्रिशिएटेड", "सराहना की", "Verb (Past)", "The teacher appreciated her effort."),
            "clarity" to GlossaryWord("Clarity", "क्लैरिटी", "स्पष्टता", "Noun", "Clarity beats speed.")
        )),
        StoryItem("s3", "The Train Ticket Counter", "रेलवे स्टेशन टिकट काउंटर पर संवाद", "Day 8–15 · Travel", listOf(
            "Mohit travelled alone to another city for the first time. At the busy station, he felt confused about the platform number.",
            "Instead of staying silent, he politely asked an officer for guidance. The officer smiled and showed him the exact schedule.",
            "Mohit boarded the train comfortably and realized that simple polite English opens helpful doors everywhere."
        ), "मोहित ने चुप रहने के बजाय विनम्रता से सवाल पूछकर सही ट्रेन पकड़ी और सीखा कि सरल English हर जगह मदद दिलाती है।", mapOf(
            "travelled" to GlossaryWord("Travelled", "ट्रैवल्ड", "यात्रा की", "Verb (Past)", "I travelled by train last week."),
            "confused" to GlossaryWord("Confused", "कन्फ्यूज़्ड", "उलझा हुआ", "Adjective", "Ask immediately if you feel confused."),
            "guidance" to GlossaryWord("Guidance", "गाइडेंस", "मार्गदर्शन", "Noun", "Good guidance saves time."),
            "schedule" to GlossaryWord("Schedule", "शेड्यूल", "समय-सारणी", "Noun", "Check the bus schedule online."),
            "boarded" to GlossaryWord("Boarded", "बोर्डेड", "चढ़ा (ट्रेन/बस में)", "Verb (Past)", "We boarded the morning train."),
            "realized" to GlossaryWord("Realized", "रियलाइज़्ड", "एहसास हुआ", "Verb (Past)", "He realized the value of practice.")
        )),
        StoryItem("s4", "Ten Minutes Before Sleep", "सोने से पहले दस मिनट का नियम", "Day 16–23 · Habit", listOf(
            "Kiran used to scroll videos for hours at night and always felt tired in the morning.",
            "She decided to replace just ten minutes with English reading and listening. She wrote three new words in a notebook every night.",
            "Within one month, her hesitation disappeared and she started helping her younger brother with his school lessons."
        ), "किरण ने रात के सिर्फ 10 मिनट पढ़ने-सुनने में लगाए और एक महीने में न सिर्फ खुद सीखा बल्कि अपने भाई को भी सिखाने लगी।", mapOf(
            "scroll" to GlossaryWord("Scroll", "स्क्रॉल", "स्क्रीन ऊपर-नीचे खिसकाना", "Verb", "Do not scroll late at night."),
            "replace" to GlossaryWord("Replace", "रिप्लेस", "बदलना / जगह लेना", "Verb", "Replace fear with practice."),
            "notebook" to GlossaryWord("Notebook", "नोटबुक", "कॉपी", "Noun", "Keep a small vocabulary notebook."),
            "hesitation" to GlossaryWord("Hesitation", "हेज़िटेशन", "झिझक", "Noun", "Speaking daily removes hesitation.")
        )),
        StoryItem("s5", "Helping a Lost Tourist", "रास्ता भटके यात्री की मदद", "Day 8–15 · Speaking", listOf(
            "Near the old fort, a tourist looked worried while checking a paper map on his phone.",
            "Suresh approached him with a friendly smile and gave simple directions using landmarks like the temple and post office.",
            "The tourist thanked Suresh warmly, and Suresh felt proud that his basic English was genuinely useful."
        ), "सुरेश ने अपनी आसान English से एक यात्री को सही रास्ता बताकर मदद की और खुद पर गर्व महसूस किया।", mapOf(
            "worried" to GlossaryWord("Worried", "वरीड", "चिंतित", "Adjective", "She looked worried before the result."),
            "approached" to GlossaryWord("Approached", "अप्रोच्ड", "पास गया", "Verb (Past)", "He approached the counter politely."),
            "directions" to GlossaryWord("Directions", "डायरेक्शंस", "दिशा / रास्ता", "Noun", "Give clear directions."),
            "landmarks" to GlossaryWord("Landmarks", "लैंडमार्क्स", "प्रमुख पहचान के स्थान", "Noun", "Mention famous landmarks when guiding."),
            "genuinely" to GlossaryWord("Genuinely", "जेन्युइनली", "सचमुच", "Adverb", "He genuinely wanted to help.")
        )),
        StoryItem("s6", "The Village Library Club", "गाँव की लाइब्रेरी का इंग्लिश क्लब", "Day 16–23 · Teamwork", listOf(
            "Four friends in a village wanted to improve their spoken English, but nobody around them spoke it fluently.",
            "They started a rule: for fifteen minutes every evening at the library, they would speak only in simple English without laughing at errors.",
            "Slowly their confidence grew, and ten more students joined their friendly evening club."
        ), "गाँव के चार दोस्तों ने बिना एक-दूसरे की गलतियों पर हँसे रोज़ 15 मिनट बोलकर पूरा क्लब खड़ा कर दिया।", mapOf(
            "fluently" to GlossaryWord("Fluently", "फ्लुएंटली", "धाराप्रवाह", "Adverb", "Speak clearly first, then fluently."),
            "errors" to GlossaryWord("Errors", "एरर्स", "गलतियाँ", "Noun", "Learn from small errors."),
            "confidence" to GlossaryWord("Confidence", "कॉन्फिडेंस", "आत्मविश्वास", "Noun", "Practice builds confidence."),
            "friendly" to GlossaryWord("Friendly", "फ्रेंडली", "मित्रतापूर्ण", "Adjective", "Create a friendly group.")
        ))
    )

    val writingTemplates = listOf(
        WritingTemplate("w1", "1 दिन की छुट्टी का ईमेल (Leave Email)", "Office / College", "Subject: Request for one day leave\n\nRespected Sir/Madam,\n\nI am not feeling well today, so I will not be able to attend the office/class. Kindly grant me leave for today (1 day).\n\nThank you,\nYour Name"),
        WritingTemplate("w2", "घर के ज़रूरी काम से छुट्टी (Urgent Leave)", "Office / College", "Subject: Application for two days leave\n\nRespected Sir/Madam,\n\nI have an urgent family work at home, so I need leave for two days from Monday to Tuesday. I will complete my pending work once I return.\n\nSincerely,\nYour Name"),
        WritingTemplate("w3", "नौकरी के लिए आवेदन ईमेल (Job Application)", "Career", "Subject: Application for Junior Executive Role\n\nDear Hiring Manager,\n\nI am writing to apply for the Junior Executive position. I am a quick learner with strong dedication and good communication skills. Please find my resume attached.\n\nWarm regards,\nYour Name"),
        WritingTemplate("w4", "शिक्षक से संदेह पूछने का संदेश", "Study", "Hello Sir/Madam,\n\nI revised today's chapter, and I have a small doubt in question number three. Whenever you are free, could you please guide me?\n\nThank you,\nYour Name"),
        WritingTemplate("w5", "गलतियों वाला वाक्य जाँचें (Test Mistake Fixer)", "Practice", "Hello sir, I did not went to college yesterday because I am agree with my cousin brother. Please revert back to me.")
    )

    fun analyzeWritingOffline(text: String): List<WritingCorrection> {
        val findings = mutableListOf<WritingCorrection>()
        val lower = text.lowercase()

        if (lower.contains("did not went") || lower.contains("didn't went")) findings.add(WritingCorrection("did not went", "did not go", "'did' के साथ हमेशा V1 ('go') आता है, 'went' नहीं।"))
        if (lower.contains("did not came") || lower.contains("didn't came")) findings.add(WritingCorrection("did not came", "did not come", "'did' के साथ हमेशा V1 ('come') आता है, 'came' नहीं।"))
        if (lower.contains("i am agree")) findings.add(WritingCorrection("I am agree", "I agree", "'Agree' एक क्रिया है, इसके पहले 'am' नहीं लगाते।"))
        if (lower.contains("cousin brother") || lower.contains("cousin sister")) findings.add(WritingCorrection("cousin brother/sister", "cousin", "सिर्फ 'cousin' काफी है; पीछे brother या sister लगाना गलत है।"))
        if (lower.contains("revert back")) findings.add(WritingCorrection("revert back", "reply / get back", "'Revert' का मतलब पहले से वापस आना होता है, 'back' न लगाएं।"))
        if (lower.contains("more better")) findings.add(WritingCorrection("more better", "much better", "'Better' खुद तुलनात्मक शब्द है, इसके आगे 'more' नहीं लगता।"))
        if (lower.contains("discuss about")) findings.add(WritingCorrection("discuss about", "discuss", "'Discuss' के बाद 'about' नहीं लगता (जैसे: Let's discuss the plan)।"))
        if (lower.contains("good name")) findings.add(WritingCorrection("what is your good name", "may I know your name", "English में 'good name' नहीं बल्कि 'your name' बोलते हैं।"))
        if (lower.contains("myself ")) findings.add(WritingCorrection("Myself [Name]", "I am [Name] / My name is [Name]", "परिचय देते समय 'Myself Rahul' बोलना गलत है; 'I am Rahul' बोलें।"))
        if (lower.contains("return back")) findings.add(WritingCorrection("return back", "return", "'Return' का अर्थ ही वापस लौटना है, 'back' अतिरिक्त है।"))
        if (lower.contains("married with")) findings.add(WritingCorrection("married with", "married to / married", "'Married' के साथ 'with' नहीं, 'to' या सीधा नाम लगाते हैं।"))
        if (lower.contains("angry on")) findings.add(WritingCorrection("angry on", "angry with", "व्यक्ति से नाराजगी के लिए 'angry with' आता है, 'on' नहीं।"))
        if (lower.contains("do the needful")) findings.add(WritingCorrection("do the needful", "please take the necessary action / please help", "आधुनिक प्रोफेशनल ईमेल में 'do the needful' के बजाय स्पष्ट अनुरोध लिखें।"))
        if (lower.contains("years back")) findings.add(WritingCorrection("years back", "years ago", "औपचारिक लेखन में 'back' की जगह 'years ago' लिखना बेहतर है।"))

        if (findings.isEmpty()) {
            findings.add(WritingCorrection("General Review", "आपका वाक्य साफ दिख रहा है! (No common literal-Hindi traps found)", "बहुत बढ़िया! बड़ी हिंदी-अनुवाद गलतियाँ नहीं मिलीं। और गहरी जाँच के लिए आप ऊपर Settings में फ्री Gemini Key जोड़ सकते हैं।"))
        }
        return findings
    }
}
