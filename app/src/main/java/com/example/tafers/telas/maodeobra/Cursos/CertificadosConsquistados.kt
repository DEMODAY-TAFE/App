package com.example.tafers.telas.maodeobra.Cursos


object CertificadosConquistados {
    val lista = mutableListOf<String>()
    fun adicionar(certificado: String) {
        if (!lista.contains(certificado)) {
            lista.add(certificado)
        }
    }
}