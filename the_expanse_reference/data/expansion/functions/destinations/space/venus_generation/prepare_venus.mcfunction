scoreboard players reset #venus tick_distributer
execute if score #venus gen_success matches 0 run tp @s 1358 384 1741 ~ -90
execute if score #venus gen_success matches 0 run scoreboard players set #venus tick_distributer 70
execute if score #venus gen_success matches 1 run function expansion:destinations/space/jupiter_generation/prepare_jupiter
scoreboard players reset #venus delay