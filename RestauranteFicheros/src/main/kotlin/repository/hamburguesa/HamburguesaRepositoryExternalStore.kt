package repository.hamburguesa

import models.Hamburguesa
import repository.IExternalStore

interface HamburguesaRepositoryExternalStore: HamburguesaRepository, IExternalStore<Hamburguesa, Int>