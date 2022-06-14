scoreboard players set @s[scores={exp_counter=4}] exp_counter 0
scoreboard players add @s exp_counter 1

execute store result score @s exp_hold_count run data get block ~ ~ ~ Items[2].Count 1
scoreboard players remove @s exp_hold_count 1
execute store result block ~ ~ ~ Items[2].Count byte 1 run scoreboard players get @s exp_hold_count

execute store result score @s exp_hold_count run data get block ~ ~ ~ Items[0].Count 1
scoreboard players remove @s exp_hold_count 1
execute store result block ~ ~ ~ Items[0].Count byte 1 run scoreboard players get @s exp_hold_count

execute if score @s exp_counter matches 4 run function expansion:blocks/arc_furnace/melt_iron/add_to_tank


