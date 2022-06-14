scoreboard players reset #earth tick_distributer
execute if score #earth gen_success matches 0 run tp @s 0 384 0 ~ -90
execute if score #earth gen_success matches 0 run scoreboard players set #earth tick_distributer 70
execute if score #earth gen_success matches 1 run function expansion:destinations/space/moon_generation/prepare_moon
scoreboard players reset #earth delay
