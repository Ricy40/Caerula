execute store result score @s spaceship_cmd run data get entity @s ArmorItems[3].tag.CustomModelData
scoreboard players add @s spaceship_cmd 1
execute store result entity @s ArmorItems[3].tag.CustomModelData int 1 run scoreboard players get @s spaceship_cmd