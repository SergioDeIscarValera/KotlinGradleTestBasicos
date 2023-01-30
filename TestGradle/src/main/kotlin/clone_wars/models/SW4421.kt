package clone_wars.models

class SW4421(escudo: Int): Droide(escudo) {
    override fun disparar(cuadricula: Array<Array<Droide?>>): Pair<Boolean, Array<Pair<Int,Int>>> {
        val result = setResult()

        val damage = getRdnDamage(30,50)
        var flag = false

        //region Cruz
        if (checkPos(cuadricula, Pair(pos.first + 1, pos.second)) &&
            cuadricula[pos.first + 1][pos.second] != null){
            cuadricula[pos.first + 1][pos.second]?.restarEscudo(damage)
            result[1] = Pair(pos.first + 1, pos.second)
            flag = true
        }
        if (checkPos(cuadricula, Pair(pos.first - 1, pos.second)) &&
            cuadricula[pos.first - 1][pos.second] != null){
            cuadricula[pos.first - 1][pos.second]?.restarEscudo(damage)
            result[5] = Pair(pos.first - 1, pos.second)
            flag = true
        }
        if (checkPos(cuadricula, Pair(pos.first, pos.second + 1)) &&
            cuadricula[pos.first][pos.second + 1] != null){
            cuadricula[pos.first][pos.second + 1]?.restarEscudo(damage)
            result[3] = Pair(pos.first, pos.second + 1)
            flag = true
        }
        if (checkPos(cuadricula, Pair(pos.first, pos.second - 1)) &&
            cuadricula[pos.first][pos.second - 1] != null){
            cuadricula[pos.first][pos.second - 1]?.restarEscudo(damage)
            result[7] = Pair(pos.first, pos.second - 1)
            flag = true
        }
        //endregion
        //region Diagonal
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
        //endregion

        return Pair(flag, result)
    }
}