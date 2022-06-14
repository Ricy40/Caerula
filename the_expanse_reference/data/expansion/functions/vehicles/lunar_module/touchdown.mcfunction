execute if predicate expansion:nbt_checks/armor/lunar_module run summon armor_stand ~ ~-2 ~ {Marker:0b,Invisible:1b,Tags:["lunar_module"],Passengers:[{id:"minecraft:pig",Silent:1b,Invulnerable:1b,NoAI:1b,NoGravity:1b,Saddle:1b,Tags:["module_pig"],ActiveEffects:[{Id:14b,Amplifier:0b,Duration:1000000,ShowParticles:0b}]}],DisabledSlots:2039583,ArmorItems:[{},{},{},{id:"minecraft:carrot_on_a_stick",Count:1b,tag:{Unbreakable:1b,CustomModelData:1012004,lunar_module:1b}}]}
execute at @e[tag=lunar_module,distance=..5,limit=1,sort=nearest] run summon villager ~ ~1.5 ~0.7 {NoGravity:1b,Silent:1b,NoAI:1b,Tags:["lunar_villager","takeoff_button"],PersistenceRequired:1b,ActiveEffects:[{Id:11b,Amplifier:5b,Duration:999999,ShowParticles:0b},{Id:14b,Amplifier:1b,Duration:999999,ShowParticles:0b}]}
effect clear @s minecraft:invisibility

function expansion:vehicles/vehicle_load
kill @e[type=armor_stand,tag=armor_storage,limit=1,sort=nearest]

tag @s remove landing_moon
