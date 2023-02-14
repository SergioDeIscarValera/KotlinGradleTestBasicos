package hospital.repositories

class HospitalRepoListTest: HospitalRepoTest() {
    override fun createRepoEmpty() = HospitalRepoList()
    override fun createRepoShort() = HospitalRepoList(3)
}