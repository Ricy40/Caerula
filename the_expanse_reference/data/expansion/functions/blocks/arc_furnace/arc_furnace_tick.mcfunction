execute unless block ~ ~ ~ minecraft:dropper run function expansion:blocks/arc_furnace/destroy
execute if block ~ ~ ~ minecraft:dropper[facing=up,triggered=false]{Items:[{Slot:0b,id:"minecraft:raw_iron"},{Slot:2b,id:"minecraft:coal"}]} run function expansion:blocks/arc_furnace/melt_iron/melting
execute if score @s exp_steel_lvl matches 124431.. run function expansion:blocks/arc_furnace/melt_iron/producing

execute unless score @s[tag=burning] exp_steel_lvl matches 124431.. unless block ~ ~ ~ minecraft:dropper[facing=up,triggered=false]{Items:[{Slot:0b,id:"minecraft:raw_iron"},{Slot:2b,id:"minecraft:coal"}]} run function expansion:blocks/arc_furnace/switch_model/turn_off
execute if entity @s[tag=burning] run function expansion:blocks/arc_furnace/particles

execute if entity @p[distance=..6] run function expansion:blocks/arc_furnace/gui

