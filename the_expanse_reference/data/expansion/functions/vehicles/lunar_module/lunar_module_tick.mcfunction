execute as @s[predicate=expansion:utility/sneak,tag=!inside_module] unless score @s optn_cooldown matches 1.. if entity @e[type=armor_stand,tag=lunar_module,distance=..2] run function expansion:vehicles/lunar_module/options
execute as @s[predicate=expansion:nbt_checks/root_vehicle/lunar_module,tag=!inside_module] run function expansion:vehicles/lunar_module/inside_tags
execute as @s[tag=inside_module,predicate=expansion:dimension/moon] as @e[type=armor_stand,tag=lunar_module,limit=1,sort=nearest] at @s run function expansion:vehicles/lunar_module/inside
execute as @s[scores={module_optns=1},tag=!inside_module] as @e[type=armor_stand,tag=lunar_module,limit=1,sort=nearest,distance=..3] at @s run function expansion:vehicles/lunar_module/demount
execute as @s[tag=inside_module] unless entity @s[predicate=expansion:nbt_checks/root_vehicle/lunar_module] run function expansion:vehicles/lunar_module/exit
execute as @s[tag=landing_moon] run function expansion:vehicles/lunar_module/landing
scoreboard players set @s[scores={module_optns=1..}] module_optns 0
