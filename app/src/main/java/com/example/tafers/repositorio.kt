// app/src/main/java/com/example/tafe/repositorio.kt
package com.example.tafers

data class CursoRepositorio(
    val titulo: String,
    val imagemResId: Int,
    val descricao: String,
    val categoria: String,
    val topicos: List<String>
)

object CursosRepositorio {
    val cursos = listOf(
        CursoRepositorio(
            titulo = "PROTEÇÃO PARA AS MÃOS",
            imagemResId = R.drawable.imgmao,
            descricao = "Treinamento sobre o uso correto de luvas de proteção e equipamentos para segurança das mãos.",
            categoria = "Elétrica",
            topicos = listOf(
                "Tipos de luvas",
                "Epis específicas",
                "Procedimentos de uso",
                "Cuidados e manutenção"
            )
        ),
        CursoRepositorio(
            titulo = "PROTEÇÃO PARA OS OLHOS",
            imagemResId = R.drawable.image,
            descricao = "Aprenda a utilizar óculos de proteção e equipamentos para segurança ocular.",
            categoria = "Construção",
            topicos = listOf(
                "Óculos de proteção",
                "Riscos comuns",
                "Limpeza e conservação"
            )
        ),
        CursoRepositorio(
            titulo = "PROTEÇÃO PARA OS PÉS",
            imagemResId = R.drawable.logo,
            descricao = "Calçados de segurança e proteção para os pés em ambientes de trabalho.",
            categoria = "Construção",
            topicos = listOf(
                "Tipos de calçados",
                "Normas de segurança",
                "Cuidados no uso"
            )
        ),
        CursoRepositorio(
            titulo = "TRABALHO EM ALTURA",
            imagemResId = R.drawable.logo,
            descricao = "Normas e procedimentos para trabalho em altura com segurança.",
            categoria = "Altura",
            topicos = listOf(
                "Segurança na altura",
                "Equipamentos obrigatórios",
                "Procedimentos de emergência"
            )
        ),
        CursoRepositorio(
            titulo = "CHOQUES ELÉTRICOS",
            imagemResId = R.drawable.logo,
            descricao = "Prevenção e proteção contra choques elétricos em ambientes industriais.",
            categoria = "Elétrica",
            topicos = listOf(
                "Riscos elétricos",
                "Prevenção de acidentes",
                "Primeiros socorros"
            )
        ),
        CursoRepositorio(
            titulo = "ESPAÇO CONFINADO",
            imagemResId = R.drawable.logo,
            descricao = "Procedimentos seguros para trabalho em espaços confinados.",
            categoria = "Outros",
            topicos = listOf(
                "Definição de espaço confinado",
                "Riscos e prevenção",
                "Equipamentos de segurança"
            )
        )
    )
}