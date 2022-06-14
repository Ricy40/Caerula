execute if score #jupiter tick_distributer matches 42 run kill @e[type=armor_stand,tag=jupiter]
execute if score #jupiter tick_distributer matches 41 run tellraw @a {"text":"Generating Jupiter... [Object 6/7]","color":"dark_purple"}

execute if score #jupiter tick_distributer matches 40 run function expansion:destinations/space/jupiter_generation/structure_blocks

execute if score #jupiter tick_distributer matches 36 run setblock ~ ~1 ~ observer[facing=up] replace
execute if score #jupiter tick_distributer matches 35 run setblock ~47 ~1 ~ observer[facing=up] replace
execute if score #jupiter tick_distributer matches 34 run setblock ~-47 ~1 ~ observer[facing=up] replace
execute if score #jupiter tick_distributer matches 33 run setblock ~ ~1 ~47 observer[facing=up] replace
execute if score #jupiter tick_distributer matches 32 run setblock ~ ~1 ~-47 observer[facing=up] replace
execute if score #jupiter tick_distributer matches 31 run setblock ~47 ~1 ~-47 observer[facing=up] replace
execute if score #jupiter tick_distributer matches 30 run setblock ~-47 ~1 ~47 observer[facing=up] replace
execute if score #jupiter tick_distributer matches 29 run setblock ~47 ~1 ~47 observer[facing=up] replace
execute if score #jupiter tick_distributer matches 28 run setblock ~-47 ~1 ~-47 observer[facing=up] replace

execute if score #jupiter tick_distributer matches 27 run setblock ~ ~-46 ~ observer[facing=up] replace
execute if score #jupiter tick_distributer matches 26 run setblock ~47 ~-46 ~ observer[facing=up] replace
execute if score #jupiter tick_distributer matches 25 run setblock ~-47 ~-46 ~ observer[facing=up] replace
execute if score #jupiter tick_distributer matches 24 run setblock ~ ~-46 ~47 observer[facing=up] replace
execute if score #jupiter tick_distributer matches 23 run setblock ~ ~-46 ~-47 observer[facing=up] replace
execute if score #jupiter tick_distributer matches 22 run setblock ~47 ~-46 ~-47 observer[facing=up] replace
execute if score #jupiter tick_distributer matches 21 run setblock ~-47 ~-46 ~47 observer[facing=up] replace
execute if score #jupiter tick_distributer matches 20 run setblock ~47 ~-46 ~47 observer[facing=up] replace
execute if score #jupiter tick_distributer matches 19 run setblock ~-47 ~-46 ~-47 observer[facing=up] replace

execute if score #jupiter tick_distributer matches 18 run setblock ~ ~48 ~ observer[facing=up] replace
execute if score #jupiter tick_distributer matches 17 run setblock ~47 ~48 ~ observer[facing=up] replace
execute if score #jupiter tick_distributer matches 16 run setblock ~-47 ~48 ~ observer[facing=up] replace
execute if score #jupiter tick_distributer matches 15 run setblock ~ ~48 ~47 observer[facing=up] replace
execute if score #jupiter tick_distributer matches 14 run setblock ~ ~48 ~-47 observer[facing=up] replace
execute if score #jupiter tick_distributer matches 13 run setblock ~47 ~48 ~-47 observer[facing=up] replace
execute if score #jupiter tick_distributer matches 12 run setblock ~-47 ~48 ~47 observer[facing=up] replace
execute if score #jupiter tick_distributer matches 11 run setblock ~47 ~48 ~47 observer[facing=up] replace
execute if score #jupiter tick_distributer matches 10 run setblock ~-47 ~48 ~-47 observer[facing=up] replace

execute if score #jupiter tick_distributer matches 3 run summon minecraft:armor_stand ~ ~ ~ {Tags:["planet_structure","jupiter"],NoGravity:1b}
execute if score #jupiter tick_distributer matches 2 run scoreboard players set #jupiter gen_success 1
execute if score #jupiter tick_distributer matches 1 run scoreboard players set #europa delay 100
scoreboard players remove #jupiter tick_distributer 1
