function expansion:handy_tools/raycast/cast
execute at @e[tag=expansion_ray,distance=..5,limit=1,sort=nearest] align xyz positioned ~.5 ~ ~.5 run function expansion:blocks/planetarium/setup
execute unless entity @s[gamemode=creative] at @e[tag=expansion_ray,distance=..5,limit=1,sort=nearest] if entity @e[type=minecraft:armor_stand,nbt={Tags:["planetarium"]},distance=..1] run item replace entity @s weapon.mainhand with air
execute at @e[tag=expansion_ray,limit=1,sort=nearest] at @e[type=armor_stand,tag=planetarium,limit=1,sort=nearest] run setblock ~ ~ ~ spawner{MaxNearbyEntities:0,RequiredPlayerRange:0} replace
execute at @e[tag=expansion_ray,limit=1,sort=nearest] run playsound minecraft:block.metal.place block @s ~ ~ ~
execute as @e[tag=expansion_ray,limit=1,sort=nearest] run kill @s