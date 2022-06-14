tp @e[type=pig,tag=module_pig,limit=1,sort=nearest] ~ -360 ~
tp @e[type=armor_stand,tag=lunar_module,limit=1,sort=nearest] ~ -360 ~
execute as @s in minecraft:overworld run tp @s ~ 900 ~

summon armor_stand ~ ~ ~ {Invisible:1b,Marker:1b,Tags:["armor_storage"]}
function expansion:vehicles/vehicle_save

loot replace entity @s armor.head loot expansion:vehicles/return_capsule_head
effect give @s minecraft:invisibility 1000 0 true
effect give @s minecraft:resistance 1000 4 true
tag @s add landing_earth
tag @s add inside_vehicle



