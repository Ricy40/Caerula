function expansion:handy_tools/raycast/cast
execute if entity @s[y_rotation=-135.1..-45] at @e[tag=expansion_ray,distance=..5,limit=1,sort=nearest] align xyz positioned ~.5 ~ ~.5 run summon armor_stand ^ ^ ^ {Invulnerable:1b,NoGravity:1b,Marker:1b,Invisible:1b,Pose:{Head:[0f,90f,0f]},Tags:["arc_furnace","expansion_block"],ArmorItems:[{},{},{},{id:"minecraft:carrot_on_a_stick",Count:1b,tag:{CustomModelData:1012306}}]}
execute if entity @s[y_rotation=45.1..135] at @e[tag=expansion_ray,distance=..5,limit=1,sort=nearest] align xyz positioned ~.5 ~ ~.5 run summon armor_stand ^ ^ ^ {Invulnerable:1b,NoGravity:1b,Marker:1b,Invisible:1b,Pose:{Head:[0f,270f,0f]},Tags:["arc_furnace","expansion_block"],ArmorItems:[{},{},{},{id:"minecraft:carrot_on_a_stick",Count:1b,tag:{CustomModelData:1012306}}]}
execute if entity @s[y_rotation=-45..45] at @e[tag=expansion_ray,distance=..5,limit=1,sort=nearest] align xyz positioned ~.5 ~ ~.5 run summon armor_stand ^ ^ ^ {Invulnerable:1b,NoGravity:1b,Marker:1b,Invisible:1b,Pose:{Head:[0f,180f,0f]},Tags:["arc_furnace","expansion_block"],ArmorItems:[{},{},{},{id:"minecraft:carrot_on_a_stick",Count:1b,tag:{CustomModelData:1012306}}]}
execute if entity @s[y_rotation=135.1..-135] at @e[tag=expansion_ray,distance=..5,limit=1,sort=nearest] align xyz positioned ~.5 ~ ~.5 run summon armor_stand ^ ^ ^ {Invulnerable:1b,NoGravity:1b,Marker:1b,Invisible:1b,Pose:{Head:[0f,360f,0f]},Tags:["arc_furnace","expansion_block"],ArmorItems:[{},{},{},{id:"minecraft:carrot_on_a_stick",Count:1b,tag:{CustomModelData:1012306}}]}
execute unless entity @s[gamemode=creative] at @e[tag=expansion_ray,distance=..5,limit=1,sort=nearest] if entity @e[type=minecraft:armor_stand,nbt={Tags:["arc_furnace"]},distance=..1] run item replace entity @s weapon.mainhand with air
execute at @e[tag=expansion_ray,limit=1,sort=nearest] at @e[type=armor_stand,tag=arc_furnace,limit=1,sort=nearest] run setblock ~ ~ ~ minecraft:dropper[facing=up]{CustomName:'{"translate":"exp_blocks_arcfurnace_name","color":"dark_grey"}'} replace
execute at @e[tag=expansion_ray,limit=1,sort=nearest] as @e[tag=arc_furnace,limit=1,sort=nearest] run scoreboard players set @s exp_steel_lvl 124430
execute at @e[tag=expansion_ray,limit=1,sort=nearest] run playsound minecraft:block.metal.place voice @s
execute as @e[tag=expansion_ray,limit=1,sort=nearest] run kill @s