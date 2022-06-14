tp @e[type=pig,tag=rocket_pig,limit=1,sort=nearest] ~ -360 ~
tp @e[type=armor_stand,tag=rocket,limit=1,sort=nearest] ~ -360 ~
execute as @s in expansion:moon run tp @s ~ 450 ~

summon armor_stand ~ ~ ~ {Invisible:1b,Marker:1b,Tags:["armor_storage"]}
function expansion:vehicles/vehicle_save

loot replace entity @s armor.head loot expansion:vehicles/lunar_module_falling
effect give @s minecraft:invisibility 1000 0 true
tag @s add landing_moon
tag @s add inside_vehicle

