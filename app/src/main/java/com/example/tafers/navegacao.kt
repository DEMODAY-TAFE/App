package com.example.tafe

sealed class Screen(val route: String) {
    object BemVindo : Screen("BemVindo")
    object Trainings : Screen("treinamentos")
    object Checklists : Screen("checklists")
    object Certificate : Screen("certificate")
    object Assistant : Screen("assistant")
    object EPIChecklist : Screen("epi_checklist")
}
