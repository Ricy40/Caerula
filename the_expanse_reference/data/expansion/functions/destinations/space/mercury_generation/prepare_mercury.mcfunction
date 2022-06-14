scoreboard players reset #mercury tick_distributer
execute if score #mercury gen_success matches 0 run tp @s 2448 384 -1605 ~ -90
execute if score #mercury gen_success matches 0 run scoreboard players set #mercury tick_distributer 70
execute if score #mercury gen_success matches 1 run function expansion:destinations/space/jupiter_generation/prepare_jupiter
scoreboard players reset #mercury delay
