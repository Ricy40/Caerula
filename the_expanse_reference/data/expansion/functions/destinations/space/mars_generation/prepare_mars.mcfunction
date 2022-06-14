scoreboard players reset #mars tick_distributer
execute if score #mars gen_success matches 0 run tp @s -1882 384 -1334 ~ -90
execute if score #mars gen_success matches 0 run scoreboard players set #mars tick_distributer 70
execute if score #mars gen_success matches 1 run function expansion:destinations/space/venus_generation/prepare_venus
scoreboard players reset #mars delay