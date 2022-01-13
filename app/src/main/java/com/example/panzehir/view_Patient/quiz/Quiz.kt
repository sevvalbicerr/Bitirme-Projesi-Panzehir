

package com.example.panzehir.view_Patient.quiz

import android.annotation.SuppressLint
import android.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.os.SystemClock
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.panzehir.R
import com.example.panzehir.databinding.QuizFragmentBinding
import com.example.panzehir.viewModelPatient.QuizViewModel
import de.hdodenhof.circleimageview.CircleImageView

class Quiz : Fragment() {
    private var score = 0
    private var correct = 0
    private var wrong = 0
    private var skip = 0
    private var qIndex = 0
    private var updateQueNo = 1

    private var questionsExpression = arrayOf(
        "Q.1. \"Babasının anlattıklarını can ............... ile dinledi.\" cümlesinde noktalı yere aşağıdaki kelimelerden hangisi gelmelidir?",
        "Q.2. \"Alın teri dökmek\" deyiminin anlamı aşağıdakilerden hangisidir?",
        "Q.3. Aşağıdaki cümlelerin hangisinde \"tat\" kelimesiyle yapılmış bir deyim vardır?",
        "Q.4. Aşağıdaki cümlelerde geçen deyimlerden hangisi \" Kalabalıktan, gürültüden uzaklaşıp dinlenmek\" anlamına gelmektedir?",
        "Q.5. Aşağıdaki cümlelerin hangisinde deyim kullanılmamıştır?",
        "Q.6. \"Etekleri ..... çalmak \" boşluğu tamamlayan kelime hangisidir?",
        "Q.7. \"Üstüne bir ……….su içmek\" boşluğu tamamlayan kelimeyi seçiniz.",
        "Q.8. \"…………..alevi gibi parlamak\" boşluğu tamamlayınız.",
        "Q.9. \"Baltayı ..... vurmak\" boşluğu tamamlayınız.",
        "Q.10. Gerekmeden her işe karışmak anlamında ................................ deyimi kullanılır. Boş bırakılan yere hangisi getirilmelidir?")
    private var answerExpression = arrayOf(
        "kulağı",
        "Bir işle çok uğraşmak.",
        "Şakayı tadında bırakmak gerek.",
        "Başını dinlemek için tatile çıktı.",
        "Ellerini güzelce yıkayıp sofraya oturdu.",
        "Zil",
        "Bardak",
        "Saman",
        "Taşa",
        "Burnunu sokmak")
    private var optionsExpression = arrayOf(
        "ağzı",
        "kulağı",
        "gözü",
        "sesi",
        "Sıcakta çok terlemek.",
        "Bir işle çok uğraşmak.",
        "Birine laf anlatmak. ",
        "Sıcaktan bunalmak.",
        "Şakayı tadında bırakmak gerek.",
        "Yemeğin tadına bakabilirsin.",
        "Tat alma organımız dildir.",
        "Bu tadı hiçbir yerde bulamazsınız",
        "Başını dinlemek için tatile çıktı.",
        "Çocuklar başı boş kaldılar.",
        "Ana kız baş başa verip konuştular.",
        "Resmini baştan savma yapmış.",
        "Satıcının ağzı iyi laf yapıyordu.",
        "Eve geç gelince babam küplere bindi",
        "Çocuk birden düşünce yüreğim hopladı.",
        "Ellerini güzelce yıkayıp sofraya oturdu.",
        "Dümbelek",
        "Flüt",
        "Zil",
        "keman",
        "Kova",
        "Leğen",
        "Şişe",
        "Bardak",
        "Odun",
        "Kömür",
        "Saman",
        "Çıra",
        "Oduna",
        "Taşa",
        "Çimene",
        "Ağaca",
        "Burnunu sokmak",
        "Burnundan solumak",
        "Burun kıvırmak",
        "Burnu sürtülmek ")

    private var questionsPoverb = arrayOf(
        "Q.1.“Gün doğmadan neler doğar.” atasözüyle aynı anlama gelen hangisidir?",
        "Q.2. “İnsanın kendi sıkıntılarına başkaları gereken önemi vermez.” anlamındaki atasözü hangisidir?",
        "Q.3. “Çalışan kimse daha yararlı hale gelir.” anlamındaki atasözü hangisidir?",
        "Q.4. Büyük ………….. ye büyük söz söyleme. Boşluğu tamamlayınız.",
        "Q.5. ..... seven dikenine katlanır. Boşluğu tamamlayınız.",
        "Q.6. İyilik eden ..... bulur. Boşluğu tamamlayınız. ",
        "Q.7. Aşağıdakilerden hangisi atasözü değildir?",
        "Q.8. Aşağıdaki atasözlerinden hangisi tutumlu olmayı öğütler?",
        "Q.9. “İnsan yedisinde ne ise yetmişinde de odur.” atasözüyle aşağıdakilerden hangisinin ilgisi yoktur?",
        "Q.10. “Horozu çok olan köyün sabahı geç olur.” atasözünün anlamı hangisidir?")
    private var answerPoverb = arrayOf(
        "Çıkmadık candan umut kesilmez.",
        "El elin eşeğini türkü çağırarak arar.",
        "İşleyen demir ışıldar.",
        "Lokma",
        "Gülü",
        "İyilik",
        "Ağzından bal akmak.",
        "Damlaya damlaya göl olur.",
        "Huyunu bilmediğin kimselere güvenme.",
        "Bir işe karışan çok olursa, o iş gecikir.")
    private var optionsPoverb = arrayOf(
        " Bir söyle iki dinle.",
        "Çıkmadık candan umut kesilmez.",
        "Gün güne uymaz.",
        "Su, başından kesilir.",

        "El elin eşeğini türkü çağırarak arar.",
        "Bükemediğin bileği öpeceksin.",
        "El elden üstündür. ",
        "Dostun attığı taş baş yarmaz.",

        "Üzüm üzüm baka baka kararır.",
        "İşleyen demir ışıldar.",
        "Dağ dağa kavuşmaz, insan insana kavuşur.",
        "Ev alma komşu al.",

        "Lokma",
        "Ekmek",
        "Et",
        "Meyve",

        "Sevgiliyi",
        "Kaktüsü",
        "Gülü",
        "Kirpiyi",

        "Para",
        "Dayak",
        "Kötülük",
        "İyilik",

        "Dünya tükenir, yalan tükenmez.",
        "Ağzından bal akmak.",
        "Dost kara günde belli olur.",
        "Ne doğrarsan aşına, o çıkar karşına.",

        "Damlaya damlaya göl olur.",
        "İşleyen demir ışıldar.",
        "Geç olsun da güç olmasın.",
        "Ağaç yaşken eğilir.",

        "Huylu huyundan ne yapılsa vazgeçmez.",
        "Can çıkmadan huy çıkar mı?",
        "Huy canın altındadır, onu değiştiremeyiz.",
        "Huyunu bilmediğin kimselere güvenme.",

        "Bir işe karışan çok olursa, o iş gecikir.",
        "Acele iş iyi olmaz.",
        "Çokluk olan yerde anlaşmazlık olur.",
        "Bir işin iyi olması için iş bölümü şart.")

    private var _binding: QuizFragmentBinding?=null
    private val binding get() = _binding!!

    private  val viewModel: QuizViewModel by lazy {
        ViewModelProvider(this).get(QuizViewModel::class.java)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding=QuizFragmentBinding.inflate(inflater,container,false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setChronomether()

        //get bundle data -- for type of quiz
        if (arguments?.getString("type")=="expression") {
            binding.imageQuestion.visibility=View.GONE
            initViews(questionsExpression)
        }
        else if(arguments?.getString("type")=="poverb"){
            binding.imageQuestion.visibility=View.GONE
            initViews(questionsPoverb)
        }
    }
    private fun setChronomether(){
        binding.chronomether.setBase(SystemClock.elapsedRealtime())
        binding.chronomether.start()
    }
    @SuppressLint("SetTextI18n")
    private fun showNextQuestion(questionList: Array<String>) {
        if (arguments?.getString("type")=="expression") {
            binding.imageQuestion.visibility=View.GONE
            checkAnswer(answerExpression)
        }
        else if(arguments?.getString("type")=="poverb"){
            binding.imageQuestion.visibility=View.GONE
            checkAnswer(answerPoverb)
        }
        binding.apply {
            if (updateQueNo < 10) {
                numberOfQuestion.text = "${updateQueNo + 1}/10"
                updateQueNo++
            }
            if (qIndex <= questionList.size - 1) {
                questionText.text = questionList[qIndex]
                A.text = optionsExpression[qIndex * 4] // 2*4=8
                B.text = optionsExpression[qIndex * 4 + 1] //  2*4+1=9
                C.text = optionsExpression[qIndex * 4 + 2] //  2*4+2=10
                D.text = optionsExpression[qIndex * 4 + 3] //  2*4+3=11
            } else {
                score = correct
                binding.chronomether.stop()
                val builder = AlertDialog.Builder(context)
                val view = LayoutInflater.from(context).inflate(R.layout.result_quiz, null)
                builder.setView(view)
                val DialogCorrectAns = view.findViewById<TextView>(R.id.correctAnswer)
                val DialogWrongAns = view.findViewById<TextView>(R.id.wronganswer)
                val Time = view.findViewById<TextView>(R.id.timeofresultquiz)
                val wrongOk = view.findViewById<CircleImageView>(R.id.UpdateButton)
                DialogCorrectAns.text = "Doğru cevaplar : $correct"
                DialogWrongAns.text = "Yanlış cevaplar : ${10-correct}"
                Time.text="Süre: ${binding.chronomether.text}"
                val alertDialog = builder.create()
                wrongOk.setOnClickListener {
                    findNavController().navigate(R.id.action_quiz2_to_categories)
                    alertDialog.dismiss()
                }
                alertDialog.show()
            }
            answerRaioGroup.clearCheck()
        }
    }
    @SuppressLint("SetTextI18n")
    private fun checkAnswer(answerList:Array<String>) {
        binding.apply {
            if (answerRaioGroup.checkedRadioButtonId == -1) {
                skip++
            } else {
                val checkRadioButton = activity!!.findViewById<RadioButton>(answerRaioGroup.checkedRadioButtonId)
                val checkAnswer = checkRadioButton.text.toString()
                if (checkAnswer == answerList[qIndex]) {
                    correct++
                    scoreText.text = "Score : $correct"
                    println("correct ${correct}")
                } else {
                    wrong++
                    println("wrong ${wrong}")
                }
            }
            qIndex++
        } }
    @SuppressLint("SetTextI18n")
    private fun initViews(questionList:Array<String>) {
        binding.apply {
            numberOfQuestion.text = questionList[qIndex]
            A.text = optionsExpression[0]
            B.text = optionsExpression[1]
            C.text = optionsExpression[2]
            D.text = optionsExpression[3]
            nextQuestionButton.setOnClickListener {
                if (answerRaioGroup.checkedRadioButtonId == -1) {
                    Toast.makeText(context,
                        "Please select an options",
                        Toast.LENGTH_SHORT)
                        .show()
                } else {
                    showNextQuestion(questionList)
                }
            }
            numberOfQuestion.text = "$updateQueNo/10"
            questionText.text = questionList[qIndex]
        }
    }
}