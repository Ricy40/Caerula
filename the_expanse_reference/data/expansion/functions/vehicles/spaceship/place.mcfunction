function expansion:handy_tools/raycast/cast
execute at @e[tag=expansion_ray,distance=..5,limit=1,sort=nearest] align xyz positioned ~.5 ~ ~.5 run summon minecraft:armor_stand ^ ^ ^ {Invulnerable:1b,Marker:0b,Invisible:1b,Tags:["exp_spaceship"],DisabledSlots:4144959}
execute at @e[tag=expansion_ray,distance=..5,limit=1,sort=nearest] run item replace entity @e[type=armor_stand,tag=exp_spaceship,limit=1,sort=nearest] armor.head from entity @s weapon.mainhand

execute at @e[tag=expansion_ray,distance=..5,limit=1,sort=nearest] as @e[type=armor_stand,tag=exp_spaceship,limit=1,sort=nearest] run function expansion:vehicles/increase_model

execute at @e[tag=expansion_ray,distance=..5,limit=1,sort=nearest] store result score @e[type=armor_stand,tag=exp_spaceship,limit=1,sort=nearest] fuel_level run data get entity @s SelectedItem.tag.fuel_lvl
execute at @e[tag=expansion_ray,distance=..5,limit=1,sort=nearest] if entity @e[type=minecraft:armor_stand,nbt={Tags:["exp_spaceship"]},distance=..1] run item replace entity @s weapon.mainhand with air
execute at @e[tag=expansion_ray,distance=..5,limit=1,sort=nearest] as @e[type=armor_stand,tag=exp_spaceship,limit=1,sort=nearest] run scoreboard players set @s fuel_max 256
kill @e[tag=expansion_ray,limit=1,sort=nearest]