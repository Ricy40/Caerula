execute if entity @s[predicate=expansion:utility/sneak,tag=!inside_rocket] unless score @s optn_cooldown matches 1.. if entity @e[type=armor_stand,tag=rocket,distance=..2] run function expansion:vehicles/rocket/options
execute if entity @s[predicate=expansion:nbt_checks/root_vehicle/rocket,tag=!inside_rocket] run function expansion:vehicles/rocket/inside_tags
execute if entity @s[tag=inside_rocket,predicate=expansion:dimension/overworld] as @e[type=armor_stand,tag=rocket,limit=1,sort=nearest] at @s run function expansion:vehicles/rocket/inside
#tag @s[scores={rocket_optns=1},tag=!inside_rocket] add is_fueling_rocket
execute if entity @s[scores={rocket_optns=2},tag=!inside_rocket] as @e[type=armor_stand,tag=rocket,limit=1,sort=nearest,distance=..3] at @s run function expansion:vehicles/rocket/demount
execute if entity @s[tag=is_fueling_rocket] as @e[type=armor_stand,tag=rocket,limit=1,sort=nearest,distance=..5] at @s run function expansion:vehicles/rocket/fuel
execute if entity @s[tag=inside_rocket] unless entity @s[predicate=expansion:nbt_checks/root_vehicle/rocket] run function expansion:vehicles/rocket/exit
scoreboard players set @s[scores={rocket_optns=1..}] rocket_optns 0
