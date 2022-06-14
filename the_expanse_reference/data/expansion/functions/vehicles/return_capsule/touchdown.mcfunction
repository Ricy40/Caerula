function expansion:vehicles/vehicle_load
kill @e[type=armor_stand,tag=armor_storage,limit=1,sort=nearest]

execute align y run summon armor_stand ~ ~ ~ {Marker:0b,Invisible:1b,Tags:["return_capsule"],DisabledSlots:2039583,ArmorItems:[{},{},{},{id:"minecraft:carrot_on_a_stick",Count:1b,tag:{Unbreakable:1b,CustomModelData:1012007,return_capsule:1b}}]}

effect clear @s minecraft:invisibility
effect clear @s minecraft:resistance
effect give @s minecraft:slow_falling 1 0 true

execute anchored eyes run particle minecraft:campfire_signal_smoke ~ ~ ~ 5 1 5 0.01 2000 force
execute as @s run playsound minecraft:entity.generic.explode master @a ~ ~ ~ 10 0.2 1

tag @s remove landing_earth
tag @s remove inside_vehicle