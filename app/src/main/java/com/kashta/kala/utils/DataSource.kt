package com.kashta.kala.utils

import com.kashta.kala.R
import com.kashta.kala.data.model.FurnitureDesign

object DataSource {

    fun getFurnitureList(): List<FurnitureDesign> = listOf(

        // SOFAS
        FurnitureDesign(1, "L-Shape Sofa", "Sofa",
            "Modern L-shaped sofa with premium cushioning, perfect for living rooms.",
            R.drawable.img_sofa2, "Teak", 42000),
        FurnitureDesign(2, "3-Seater Sofa", "Sofa",
            "Classic 3-seater sofa with wooden frame and fabric upholstery.",
            R.drawable.img_sofa3, "Sal", 28000),
        FurnitureDesign(3, "2-Seater Loveseat", "Sofa",
            "Compact 2-seater sofa ideal for small living spaces.",
            R.drawable.img_sofa1, "Plywood", 18000),
        FurnitureDesign(4, "Recliner Sofa", "Sofa",
            "Single recliner sofa with adjustable backrest for maximum comfort.",
            R.drawable.img_sofa2, "Teak", 22000),
        FurnitureDesign(5, "Sectional Sofa", "Sofa",
            "Large sectional sofa with chaise lounge for spacious living rooms.",
            R.drawable.img_sofa1, "Sal", 55000),
        FurnitureDesign(6, "Wooden Sofa Set", "Sofa",
            "Traditional wooden sofa set with cushions, includes 1+2+3 seater.",
            R.drawable.img_sofa3, "Teak", 65000),

        // BEDS
        FurnitureDesign(7, "King Size Bed", "Bed",
            "Spacious king size bed with solid wood frame and storage drawers.",
            R.drawable.img_bed1, "Teak", 35000),
        FurnitureDesign(8, "Queen Size Bed", "Bed",
            "Queen size bed with upholstered headboard and under-bed storage.",
            R.drawable.img_bed2, "Sal", 25000),
        FurnitureDesign(9, "Single Bed", "Bed",
            "Single bed with wooden slats, perfect for children's rooms.",
            R.drawable.img_bed1, "Plywood", 12000),
        FurnitureDesign(10, "Bunk Bed", "Bed",
            "Double bunk bed with safety rails and ladder, ideal for kids.",
            R.drawable.img_bed2, "Sal", 22000),
        FurnitureDesign(11, "Diwan Bed", "Bed",
            "Traditional diwan with storage box, can double as sofa.",
            R.drawable.img_bed1, "Teak", 18000),
        FurnitureDesign(12, "Platform Bed", "Bed",
            "Modern low-profile platform bed with minimalist design.",
            R.drawable.img_bed2, "MDF", 20000),

        // CABINETS
        FurnitureDesign(13, "4-Door Wardrobe", "Cabinet",
            "Spacious 4-door wardrobe with mirror and internal shelves.",
            R.drawable.img_cabinet1, "Plywood", 28000),
        FurnitureDesign(14, "2-Door Wardrobe", "Cabinet",
            "Compact 2-door wardrobe with hanging space and drawers.",
            R.drawable.img_cabinet2, "MDF", 16000),
        FurnitureDesign(15, "TV Unit Cabinet", "Cabinet",
            "Modern TV unit with shelves and cable management.",
            R.drawable.img_cabinet2, "MDF", 12000),
        FurnitureDesign(16, "Kitchen Cabinet", "Cabinet",
            "Modular kitchen cabinet with upper and lower units.",
            R.drawable.img_cabinet1, "Plywood", 45000),
        FurnitureDesign(17, "Shoe Rack Cabinet", "Cabinet",
            "6-shelf shoe rack cabinet with doors for neat storage.",
            R.drawable.img_cabinet2, "Plywood", 8000),
        FurnitureDesign(18, "Bookshelf Unit", "Cabinet",
            "5-tier open bookshelf with adjustable shelves.",
            R.drawable.img_cabinet1, "MDF", 10000),

        // TABLES
        FurnitureDesign(19, "6-Seater Dining Table", "Table",
            "Solid wood dining table with 6 chairs included.",
            R.drawable.img_table1, "Teak", 38000),
        FurnitureDesign(20, "4-Seater Dining Table", "Table",
            "Compact dining table set with 4 chairs for small families.",
            R.drawable.img_table1, "Sal", 24000),
        FurnitureDesign(21, "Coffee Table", "Table",
            "Low centre table with glass top and wooden frame.",
            R.drawable.img_table2, "Teak", 8000),
        FurnitureDesign(22, "Study Table", "Table",
            "Student study table with bookshelf and drawer unit.",
            R.drawable.img_table2, "MDF", 9000),
        FurnitureDesign(23, "Computer Desk", "Table",
            "L-shaped computer desk with keyboard tray and CPU stand.",
            R.drawable.img_table2, "MDF", 12000),
        FurnitureDesign(24, "Dressing Table", "Table",
            "Dressing table with large mirror and 4 drawers.",
            R.drawable.img_table1, "Plywood", 14000),

        // DOORS
        FurnitureDesign(25, "Main Door Panel", "Door",
            "Solid teak main door with carving and polish finish.",
            R.drawable.img_door1, "Teak", 18000),
        FurnitureDesign(26, "Bedroom Door", "Door",
            "Flush door with laminate finish for bedroom use.",
            R.drawable.img_door1, "Plywood", 6000),
        FurnitureDesign(27, "Sliding Door", "Door",
            "Modern sliding door for wardrobes with soft-close track.",
            R.drawable.img_door1, "MDF", 9000),
        FurnitureDesign(28, "French Door", "Door",
            "Double-leaf French door with glass panels for balcony.",
            R.drawable.img_door1, "Teak", 22000),
        FurnitureDesign(29, "Folding Door", "Door",
            "Bi-fold door for compact spaces like bathrooms and closets.",
            R.drawable.img_door1, "Plywood", 7000),
        FurnitureDesign(30, "Pooja Room Door", "Door",
            "Carved wooden pooja room door with traditional design.",
            R.drawable.img_door1, "Teak", 15000)
    )

    val categories = listOf("All", "Sofa", "Bed", "Cabinet", "Table", "Door")
}