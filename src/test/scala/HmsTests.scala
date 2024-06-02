import org.scalatest.BeforeAndAfterAll
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.must.Matchers._

class HmsTests extends AnyFlatSpec with SparkSpec with HmsSpec with BeforeAndAfterAll {
    override protected def beforeAll(): Unit = {
        super.beforeAll()
        createAllTables
    }

    override protected def afterAll(): Unit = {
        super.afterAll()
        dropAllTables
    }

    it should "read all HMS scripts create tables " in {
        noException should be thrownBy spark.table("person").show(truncate = false)
    }
}
