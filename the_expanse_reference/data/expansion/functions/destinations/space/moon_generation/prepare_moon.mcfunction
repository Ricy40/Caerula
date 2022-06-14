scoreboard players reset #moon tick_distributer
execute if score #moon gen_success matches 0 run tp @s -621 384 126 ~ -90
execute if score #moon gen_success matches 0 run scoreboard players set #moon tick_distributer 70
execute if score #moon gen_success matches 1 run function expansion:destinations/space/mars_generation/prepare_mars
scoreboard players reset #moon delay
