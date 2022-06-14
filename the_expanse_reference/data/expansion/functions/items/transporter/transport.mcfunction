# summon return armor stand which also has the model
execute align xz run summon armor_stand ~.5 ~ ~.5 {Tags:["room_origin"],Marker:1b,Invisible:1b,ArmorItems:[{},{},{},{id:"minecraft:carrot_on_a_stick",Count:1b,tag:{CustomModelData:1012204}}]}

# cosmetics
execute at @e[type=armor_stand,tag=room_origin,limit=1,sort=nearest] run particle minecraft:squid_ink ~ ~1 ~ 0.5 0.5 0.5 0.2 50
execute at @e[type=armor_stand,tag=room_origin,limit=1,sort=nearest] run particle minecraft:end_rod ~ ~1 ~ 0 0 0 0.2 50
playsound entity.lightning_bolt.impact player @a ~ ~ ~

# set the room_id of the armor stand equal to the room_id of the player
scoreboard players operation @e[type=armor_stand,tag=room_origin,limit=1,sort=nearest] exp_room_id = @s exp_room_id

# determine the unique position of the room using the room_id
execute in expansion:transporter_utility positioned 0 128 0 align xyz run tp @e[type=armor_stand,tag=room_position,limit=1,sort=nearest] ~ ~ ~
execute in expansion:transporter_utility positioned 16 128 0 store result entity @e[type=armor_stand,tag=room_position,limit=1,sort=nearest] Pos[0] double 0.01 run scoreboard players get @s exp_room_id

# teleport the player to the unique position in the storage dimension
execute in expansion:transporter_utility positioned 16 128 0 at @e[type=armor_stand,tag=room_position,limit=1,sort=nearest] in expansion:storage align xyz run tp @s ~0.5 128 ~0.5

# generate the room
execute if entity @s[tag=generate_room] at @e[type=armor_stand,tag=room_position,limit=1,sort=nearest] in expansion:storage run function expansion:items/transporter/generate_room

# kill the room position armorstand, it has fulfilled its purpose
execute as @e[type=armor_stand,tag=room_position] if score @s exp_room_id = @p exp_room_id run kill @s

# remove forceload in previous dimension
execute in expansion:transporter_utility run forceload remove 0 0
