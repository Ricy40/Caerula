# assign scores to players without a room
scoreboard players reset @s exp_room_id
execute store result score @s exp_room_id run data get entity @s SelectedItem.tag.room_id
execute unless score @s exp_room_id matches 1.. run function expansion:items/transporter/scores

forceload add ~ ~

# summon armor stand that will regulate the teleport
summon armor_stand ~ ~ ~ {Tags:["room_position"],Marker:1b,Invisible:1b}

# summon return armor stand which also has the model
execute align xz run summon armor_stand ~.5 ~ ~.5 {Tags:["room_origin"],Marker:1b,Invisible:1b,ArmorItems:[{},{},{},{id:"minecraft:carrot_on_a_stick",Count:1b,tag:{CustomModelData:1012204}}]}

# cosmetics
execute at @e[type=armor_stand,tag=room_origin,limit=1,sort=nearest] run particle minecraft:squid_ink ~ ~1 ~ 0.5 0.5 0.5 0.2 50
execute at @e[type=armor_stand,tag=room_origin,limit=1,sort=nearest] run particle minecraft:end_rod ~ ~1 ~ 0 0 0 0.2 50
playsound entity.lightning_bolt.impact player @a ~ ~ ~

# set the room_id of the room_origin armor stand equal to the room_id of the player
scoreboard players operation @e[type=armor_stand,tag=room_origin,limit=1,sort=nearest] exp_room_id = @s exp_room_id

# run the function that determines the position of the room and teleport the player there
execute as @e[type=armor_stand,tag=room_position,limit=1,sort=nearest] run function expansion:items/transporter/room_position

# add tag to prevent function looping
tag @s add roomtag1


