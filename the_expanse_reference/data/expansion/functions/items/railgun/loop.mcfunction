execute if block ^ ^ ^0.2 #expansion:expansion_air run tp @s ^ ^ ^0.5
particle mycelium ~ ~ ~ 0 0 0 0.5 1 force
execute as @e[type=#expansion:sentient,tag=!shooting,distance=..2,limit=1,sort=nearest] unless score @s exp_damage matches 1.. run function expansion:items/railgun/damage
scoreboard players add @s exp_max_range 1
execute as @s[scores={exp_max_range=..200}] at @s if block ^ ^ ^0.2 #expansion:expansion_air run function expansion:items/railgun/loop