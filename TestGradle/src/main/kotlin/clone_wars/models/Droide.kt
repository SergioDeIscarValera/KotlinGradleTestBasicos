package clone_wars.models

abstract class Droide(private var escudo: Int) {
    protected var pos: Pair<Int,Int> = Pair(0,0)

    fun getEscudo(): Int{
        return escudo
    }

    fun getPosDroide(): Pair<Int,Int>{
        return pos
    }

    fun restarEscudo(value:Int){
        if (escudo - value <= 0){
            escudo = 0
        }else{
            escudo -= value
        }
    }

    fun setPos(cuadricula: Array<Array<Droide?>>, pos: Pair<Int, Int>){
        require(pos.first in cuadricula.indices &&
                pos.second in cuadricula[0].indices){"Error -> Posición fuera del mapa"}
        this.pos = pos
    }

    protected fun checkPos(cuadricula: Array<Array<Droide?>>, pos: Pair<Int, Int>): Boolean{
        return pos.first in cuadricula.indices && pos.second in cuadricula[0].indices
    }

    protected fun setResult(): Array<Pair<Int,Int>>{
        return Array(8){ Pair(-1,-1) }
    }

    protected fun getRdnDamage(basic: Int = 1, critical: Int = 5): Int{
        return if((0..100).random() > 15) basic else critical
    }

    abstract fun disparar(cuadricula: Array<Array<Droide?>>): Pair<Boolean, Array<Pair<Int,Int>>>
}