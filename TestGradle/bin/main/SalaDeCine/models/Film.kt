package SalaDeCine.models

import SalaDeCine.enums.FilmGenero

data class Film (val titulo: String, val year: UShort, val director: String, val genero: FilmGenero )