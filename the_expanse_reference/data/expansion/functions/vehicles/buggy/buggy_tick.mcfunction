execute if entity @s[predicate=expansion:utility/sneak,tag=!inside_buggy] unless score @s optn_cooldown matches 1.. if entity @e[type=armor_stand,tag=moon_buggy,distance=..2] run function expansion:vehicles/buggy/options
execute if entity @s[predicate=expansion:nbt_checks/root_vehicle/buggy,tag=!inside_buggy] run function expansion:vehicles/buggy/inside_tags
execute if entity @s[tag=inside_buggy] unless score @s expansion_dim matches 3 unless score @s expansion_dim matches 7 run function expansion:vehicles/buggy/inside
tag @s[scores={buggy_optns=1},tag=!inside_buggy] add is_fueling_buggy
execute if entity @s[scores={buggy_optns=2},tag=!inside_buggy] as @e[type=armor_stand,tag=moon_buggy,limit=1,sort=nearest,distance=..3] at @s run function expansion:vehicles/buggy/demount
execute if entity @s[tag=is_fueling_buggy] as @e[type=armor_stand,tag=moon_buggy,limit=1,sort=nearest,distance=..5] at @s run function expansion:vehicles/buggy/fuel
execute if entity @s[tag=inside_buggy] unless entity @s[predicate=expansion:nbt_checks/root_vehicle/buggy] run function expansion:vehicles/buggy/exit
scoreboard players set @s[scores={buggy_optns=1..}] buggy_optns 0
