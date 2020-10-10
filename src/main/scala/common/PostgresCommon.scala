package common

import java.util.Properties
import org.slf4j.LoggerFactory

import org.apache.spark.sql.{DataFrame, SparkSession}
import org.slf4j.LoggerFactory

object PostgresCommon {
  private val logger = LoggerFactory.getLogger(getClass.getName)

    def getPostgresCommonProps(): Properties = {
    // postgresql
    logger.info("getPostgresCommonProps started ")
    val pgConnectionProperties = new Properties()
    pgConnectionProperties.put("user", "postgres")
    pgConnectionProperties.put("password", "123")
    pgConnectionProperties
    }


  def getPostgreServerDatabase(): String = {
    val pgURL = "jdbc:postgresql://localhost:5432/futurex"
    pgURL
    }

  def fetchDataFrameFromPgTable(spark: SparkSession, pgTable: String) :Option[DataFrame] = {
  try {
   logger.info("fetch postgres data")
   val pgProp = getPostgresCommonProps
   logger.info("Creating Dataframe from postgress")
   val pgCourseDataframe = spark.read.jdbc(getPostgreServerDatabase, pgTable, pgProp)
   Some(pgCourseDataframe)
  }
  catch {
    case e: Exception =>
      logger.error("An error has occured in the fetchDataFrameFromPgTable method " + e.printStackTrace())
      System.exit(1)
      None
  }

  }

}




