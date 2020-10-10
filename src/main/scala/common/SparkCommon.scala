package common

import common.PostgresCommon.logger
import org.apache.spark.sql.SparkSession
import org.slf4j.LoggerFactory

object SparkCommon {

  private val logger = LoggerFactory.getLogger(getClass.getName)

  def createSparkSession(): Option[SparkSession] = {
    try {
      // create a spark session
      //for windows
      // spark.sql.warehouse.dir ('file:/E:/UDEMY%20COURSES/Spark%20Unit%20Testing/Idea/spark-warehouse').
      System.setProperty("hadoop.home.dir", "c:\\winutils")
      logger.info("create dataframe started ")
      val spark = SparkSession.builder
        .master("local")
        .appName("HelloSpark")
        .enableHiveSupport()
        .config("spark.sql.warehouse.dir", "spark-warehouse")
        .getOrCreate()
      Some(spark)
      //val sampleSeq = Seq((1,"spark"),(2,"Big Data"))
      //val df = spark.createDataFrame(sampleSeq).toDF("course id","course name")
      //df.show
    }
    catch {
      case e: Exception =>
        logger.error("An error has occured in the createSparkSession method " + e.printStackTrace())
        System.exit(1)
        None
    }
  }
}