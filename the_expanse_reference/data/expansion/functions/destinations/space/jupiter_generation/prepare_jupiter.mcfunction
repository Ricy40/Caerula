scoreboard players reset #jupiter tick_distributer
execute if score #jupiter gen_success matches 0 run tp @s -2873 384 1536 ~ -90
execute if score #jupiter gen_success matches 0 run scoreboard players set #jupiter tick_distributer 70
execute if score #jupiter gen_success matches 1 run function expansion:destinations/space/europa_generation/prepare_europa
scoreboard players reset #jupiter delay
