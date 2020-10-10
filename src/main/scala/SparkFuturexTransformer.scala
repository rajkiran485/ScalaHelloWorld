import java.util.Properties

import common.{PostgresCommon, SparkCommon}
import org.apache.spark.sql.{DataFrame, SparkSession}
import org.slf4j.LoggerFactory

object SparkFuturexTransformer {

  private val logger = LoggerFactory.getLogger(getClass.getName)

  def main(args:Array[String]): Unit = {
    try{
    logger.info("main method")
    logger.warn("this is a warning")
    logger.info("Hello world My first spark program")
    val spark = SparkCommon.createSparkSession().get
    //df.write.format("csv").save("sampleSeq")
    logger.info("fetch postgres data")
    val pgTable = "futurexschema.futurex_course_catalog"
    val pgCourseDataframe  = PostgresCommon.fetchDataFrameFromPgTable(spark,pgTable).get
    logger.info(pgTable +  "table Data fetched")
    pgCourseDataframe.show()
    pgCourseDataframe
    }
    catch{
      case e: Exception =>
        logger.error("An error has occured in the main method " + e.printStackTrace())
    }
  }
}
