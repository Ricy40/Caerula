execute if block ~ ~ ~ minecraft:dropper{Items:[{Slot:0b}]} unless block ~ ~ ~ minecraft:dropper{Items:[{Slot:2b}]} store result score @s exp_hold_count run data get block ~ ~ ~ Items[7].Count 1
execute if block ~ ~ ~ minecraft:dropper{Items:[{Slot:2b}]} unless block ~ ~ ~ minecraft:dropper{Items:[{Slot:0b}]} store result score @s exp_hold_count run data get block ~ ~ ~ Items[7].Count 1
execute if block ~ ~ ~ minecraft:dropper{Items:[{Slot:0b},{Slot:2b}]} store result score @s exp_hold_count run data get block ~ ~ ~ Items[8].Count 1
execute unless block ~ ~ ~ minecraft:dropper{Items:[{Slot:0b}]} unless block ~ ~ ~ minecraft:dropper{Items:[{Slot:2b}]} store result score @s exp_hold_count run data get block ~ ~ ~ Items[6].Count 1

scoreboard players add @s exp_hold_count 1
item modify block ~ ~ ~ container.8 expansion:blocks/arc_furnace/set_steel_count

scoreboard players set @s[scores={exp_counter_2=3}] exp_counter_2 0
scoreboard players add @s exp_counter_2 1

scoreboard players remove @s[scores={exp_counter_2=3}] exp_steel_lvl 1
execute if score @s exp_counter_2 matches 3 run function expansion:blocks/arc_furnace/melt_iron/change_tank_model