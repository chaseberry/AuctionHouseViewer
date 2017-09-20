package edu.chase.csh.taladitepricecaculator.models

import android.os.Parcel
import android.os.Parcelable
import edu.csh.chase.kjson.Json
import edu.csh.chase.kjson.JsonSerializable

//0:Realm Name,
// 1:Export Time,
// 2:PMktPrice Date,
// 3:Reserved,
// 4:Item ID,
// 5:Item Name,
// 6:AH MarketPrice Coppers,
// 7:AH Quantity,
// 8:AH MarketPrice,
// 9:AH MinimumPrice,
// 10:14-day Median Market Price,
// 11:Median Market Price StdDev,
// 12:14-day Todays PMktPrice,
// 13:PMktPrice StdDev,
// 14:Daily Price Change,
// 15:Avg Daily Posted,
// 16:Avg Estimated Daily Sold,
// 17:Estimated Demand
data class Item(val id: Int, val name: String, val lowestPrice: Int, val quantity: Int, val medianPrice: Int, val priceTrend: Double, val demand: Double) : JsonSerializable {

    override fun jsonSerialize(): String {
        return Json(
                "id" to id,
                "name" to name
        ).toString()
    }

    companion object {

        fun parse(str: String): Item? {
            return try {
                val parts = str.split(",")
                val id = parts[4].toInt()
                val name = parts[5]
                val lowPrice = parts[9].toDouble().toInt()
                val quantity = parts[7].toInt()
                val medPrice = parts[10].toDouble().toInt()
                val priceTrend = parts[14].toDouble()
                val demand = parts[17].toDouble()

                return Item(id, name, lowPrice, quantity, medPrice, priceTrend, demand)
            } catch(e: Exception) {
                null
            }
        }

    }

}