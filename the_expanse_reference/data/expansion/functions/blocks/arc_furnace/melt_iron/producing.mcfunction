execute unless score @s exp_timer_2 matches 80.. run scoreboard players add @s exp_timer_2 1

execute if score @s exp_timer_2 matches 80 if score @s exp_steel_lvl matches 124431..124446 unless block ~ ~ ~ minecraft:dropper{Items:[{Slot:8b,id:"minecraft:jigsaw",tag:{steel_ingot:1b},Count:64b}]} run function expansion:blocks/arc_furnace/melt_iron/produce_steel

execute unless entity @s[tag=burning] run function expansion:blocks/arc_furnace/switch_model/turn_on