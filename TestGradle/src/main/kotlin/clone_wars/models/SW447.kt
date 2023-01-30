package clone_wars.models

class SW447(escudo: Int): Droide(escudo) {
    override fun disparar(cuadricula: Array<Array<Droide?>>): Pair<Boolean, Array<Pair<Int,Int>>> {
        val result = setResult()

        val damage = getRdnDamage(2,4)
        var flag = false

        if (checkPos(cuadricula, Pair(pos.first + 1, pos.second - 1)) &&
            cuadricula[pos.first + 1][pos.second - 1] != null){
            cuadricula[pos.first + 1][pos.second -1]?.restarEscudo(damage)
            result[0] = Pair(pos.first + 1, pos.second - 1)
            flag = true
        }
        if (checkPos(cuadricula, Pair(pos.first + 1, pos.second + 1)) &&
            cuadricula[pos.first + 1][pos.second + 1] != null){
            cuadricula[pos.first + 1][pos.second + 1]?.restarEscudo(damage)
            result[2] = Pair(pos.first + 1, pos.second + 1)
            flag = true
        }
        if (checkPos(cuadricula, Pair(pos.first - 1, pos.second - 1)) &&
            cuadricula[pos.first - 1][pos.second - 1] != null){
            cuadricula[pos.first - 1][pos.second - 1]?.restarEscudo(damage)
            result[6] = Pair(pos.first - 1, pos.second - 1)
            flag = true
        }
        if (checkPos(cuadricula, Pair(pos.first - 1, pos.second + 1)) &&
            cuadricula[pos.first - 1][pos.second + 1] != null){
            cuadricula[pos.first - 1][pos.second + 1]?.restarEscudo(damage)
            result[4] = Pair(pos.first - 1, pos.second + 1)
            flag = true
        }

        return Pair(flag, result)
    }
}