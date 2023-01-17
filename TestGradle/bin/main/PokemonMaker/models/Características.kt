package PokemonMaker.models

class Características(var PS: UInt, val ataque: UInt, val defensa: UInt, val ataEspecial: UInt, val defEspecial: UInt, val velocidad: UInt){
    override fun toString(): String {
        return """"Características":{ "PS":$PS, "ataque":$ataque, "defensa":$defensa, "ataEspecial":$ataEspecial, "defEspecial":$defEspecial, "velocidad":$velocidad }"""
    }
}