execute if block ~ ~ ~ minecraft:dropper{Items:[{Slot:8b,id:"minecraft:jigsaw",tag:{steel_ingot:1b}}]} run function expansion:blocks/arc_furnace/melt_iron/add_one_steel
execute unless block ~ ~ ~ minecraft:dropper{Items:[{Slot:8b}]} run item replace block ~ ~ ~ container.8 with jigsaw{steel_ingot:1b,CustomModelData:123459,display:{Name:'{"italic":false,"color":"white","translate":"exp_items_steelingot_name"}'}}


scoreboard players set @s exp_timer_2 0
execute if score @s exp_steel_lvl matches 124430 run scoreboard players reset @s exp_timer_2

