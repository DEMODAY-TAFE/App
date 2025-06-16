// app/src/main/java/com/example/tafe/repositorio.kt
package com.example.tafers

data class QuizQuestion(
    val pergunta: String,
    val alternativas: List<String>,
    val respostaCorreta: Int // índice da alternativa correta
)

data class CursoRepositorio(
    val titulo: String,
    val imagemResId: Int,
    val descricao: String,
    val categoria: String,
    val topicos: List<String>,
    val videoUrl: String? = null,
    val quiz: List<QuizQuestion>
)

object CursosRepositorio {
    val cursos = listOf(
        CursoRepositorio(
            titulo = "PROTEÇÃO PARA AS MÃOS",
            imagemResId = R.drawable.imgmao,
            descricao = "Treinamento sobre o uso correto de luvas de proteção em diferentes ambientes de trabalho.",
            categoria = "EPIs - Mãos",
            topicos = listOf(
                "Seleção por risco (mecânico, químico, térmico)",
                "Norma NR 6 - Certificação CA",
                "Teste de estanqueidade",
                "Descarte seguro"
            ),
            videoUrl = "https://youtu.be/dM8sgLqXxbw",
            quiz = listOf(
                QuizQuestion(
                    pergunta = "Qual luva protege contra riscos químicos e cortes?",
                    alternativas = listOf("Latex", "Kevlar", "Nitrile com malha de aço", "Algodão"),
                    respostaCorreta = 2
                )
            )
        ),
        CursoRepositorio(
            titulo = "PROTEÇÃO AUDITIVA",
            imagemResId = R.drawable.imgouvido,
            descricao = "Uso correto de protetores auriculares e abafadores de ruído.",
            categoria = "EPIs - Ouvidos",
            topicos = listOf(
                "NR 15 - Limites de exposição ao ruído",
                "Tipos: plug vs. abafador",
                "NRR (Noise Reduction Rating)",
                "Higienização diária"
            ),
            videoUrl = "https://youtu.be/YBJkDX6AW64",
            quiz = listOf(
                QuizQuestion(
                    pergunta = "Qual o NRR mínimo recomendado para 85dB?",
                    alternativas = listOf("10dB", "15dB", "20dB", "25dB"),
                    respostaCorreta = 2
                )
            )
        ),
        CursoRepositorio(
            titulo = "PROTEÇÃO PARA A CABEÇA",
            imagemResId = R.drawable.imgcapacete,
            descricao = "Normas e procedimentos para uso correto de capacetes de segurança.",
            categoria = "EPIs - Cabeça",
            topicos = listOf(
                "Classes de impacto (ABET)",
                "Compatibilidade com outros EPIs",
                "Substituição após impacto"
            ),
            videoUrl = "https://youtu.be/5Tt7iMxHLq8",
            quiz = listOf(
                QuizQuestion(
                    pergunta = "Qual componente NÃO faz parte do sistema de absorção de impacto?",
                    alternativas = listOf("Arnês", "Casco", "Jugular", "Viseira"),
                    respostaCorreta = 3
                )
            )
        ),
        CursoRepositorio(
            titulo = "PROTEÇÃO VISUAL",
            imagemResId = R.drawable.imgoculos,
            descricao = "Uso adequado de óculos e protetores faciais.",
            categoria = "EPIs - Olhos",
            topicos = listOf(
                "Proteção contra UV/IR",
                "Lentes fotocromáticas",
                "Viseiras para solda"
            ),
            videoUrl = "https://youtu.be/uTWN8992ogA",
            quiz = listOf(
                QuizQuestion(
                    pergunta = "Qual símbolo indica proteção contra solda?",
                    alternativas = listOf("UV+", "W+", "IR-5", "EN166"),
                    respostaCorreta = 3
                )
            )
        ),
        CursoRepositorio(
            titulo = "PROTEÇÃO CONTRA QUEDAS",
            imagemResId = R.drawable.imgcinto,
            descricao = "Sistemas completos de segurança para trabalho em altura.",
            categoria = "EPIs - Quedas",
            topicos = listOf(
                "NR 35 - Requisitos",
                "Fator de queda",
                "Ancoragem estrutural"
            ),
            videoUrl = "https://youtu.be/hogl9BmMO_c",
            quiz = listOf(
                QuizQuestion(
                    pergunta = "Qual distância máxima livre de queda?",
                    alternativas = listOf("1m", "1.5m", "2m", "6m"),
                    respostaCorreta = 1
                )
            )
        )
    )
}