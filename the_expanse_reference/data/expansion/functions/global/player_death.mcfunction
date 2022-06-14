execute as @s[tag=inside_spaceship] run function expansion:vehicles/spaceship/exit
execute as @s[tag=inside_module] run function expansion:vehicles/lunar_module/exit
execute as @s[tag=inside_rocket] run function expansion:vehicles/rocket/exit
execute as @s[tag=inside_buggy] run function expansion:vehicles/buggy/exit

execute as @s[tag=roomtag1,tag=!roomtag2] at @s run function expansion:items/transporter/return

scoreboard players set @s exp_death 0