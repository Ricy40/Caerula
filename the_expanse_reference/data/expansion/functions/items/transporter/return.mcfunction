# add a tag to the origin armor stand with the matching room_id
execute as @e[type=armor_stand,tag=room_origin] if score @s exp_room_id = @p exp_room_id run tag @s add recieving_tp

execute if entity @s[nbt={SelectedItem:{tag:{room_id:0}}}] run function expansion:items/transporter/copy_room_id

# tp player to origin armor stand
execute at @e[type=armor_stand,tag=recieving_tp,limit=1] run tp @s ~ ~ ~

playsound entity.lightning_bolt.impact player @a ~ ~ ~

# remove the forceload
execute at @e[type=armor_stand,tag=recieving_tp,limit=1] run forceload remove ~ ~

# set 10 second cooldown(for balancing, not to 'fix' issues of course)
scoreboard players set @s exp_cooldown 1

# kill the origin armor stand
kill @e[tag=recieving_tp]

# function looping prevention
tag @s remove roomtag1
tag @s remove roomtag2

