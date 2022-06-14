scoreboard players reset #europa tick_distributer
execute if score #europa gen_success matches 0 run tp @s -3557 384 1635 ~ -90
execute if score #europa gen_success matches 0 run scoreboard players set #europa tick_distributer 70
execute if score #europa gen_success matches 1 run function expansion:destinations/space/finish_generation
scoreboard players reset #europa delay
