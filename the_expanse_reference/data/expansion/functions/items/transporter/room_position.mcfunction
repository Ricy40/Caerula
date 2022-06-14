# set the room_id of the armor stand equal to the room_id of the player
scoreboard players operation @s exp_room_id = @p exp_room_id

# determine the unique position of the room using the room_id
execute in expansion:transporter_utility run tp @s 16 128 0
execute in expansion:transporter_utility positioned 16 128 0 store result entity @s Pos[0] double 0.01 run scoreboard players get @s exp_room_id

tag @p add tp_to_room
# teleport the player to the unique position in the storage dimension
execute in expansion:transporter_utility at @s in expansion:storage align xyz run tp @p[tag=tp_to_room] ~0.5 128 0.5
tag @p remove tp_to_room

# generate the room
execute at @p[tag=generate_room] in expansion:storage run function expansion:items/transporter/generate_room

# kill the room position armorstand, it has fulfilled its purpose
execute if score @s exp_room_id = @p exp_room_id run kill @s