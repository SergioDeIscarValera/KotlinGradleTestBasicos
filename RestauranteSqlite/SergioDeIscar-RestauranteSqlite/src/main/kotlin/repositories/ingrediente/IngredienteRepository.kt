package repositories.ingrediente

import models.Ingrediente
import repositories.IExternalStore

interface IngredienteRepository: IngredienteExtension, IExternalStore<Ingrediente>