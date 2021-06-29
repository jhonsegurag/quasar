package com.co.meli.quasar.service;

import com.co.meli.quasar.entity.WordNode;
import com.co.meli.quasar.exception.MessageException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class MessageServiceImpl implements IMessageService{
    @Override
    public String getMessage(List<List<String>> messages) throws MessageException {
        List<List<String>> messagesWithOutGap = removeGapMessage(messages, calculateSizeMessage(messages));
        List<WordNode> wordNodes = transformWorkNodes(messagesWithOutGap);
        String message = proccesWordNodes(wordNodes);
        return message;
    }

    public List<WordNode> transformWorkNodes(List<List<String>> messages) {
        List<WordNode> wordNodes = new ArrayList<>();
        for (int i = 0; i < messages.size(); i++) {
            List<String> message = messages.get(i);
            for (int j = 0; j < message.size(); j++) {
                String wordPivot = message.get(j);
                wordNodes.add(new WordNode(j, wordPivot));
            }
        }
        return wordNodes;
    }

    public List<List<String>> removeGapMessage(List<List<String>> messages, int sizeMessage){
        int subListIndex = 0;
        for(int i = 0; i < messages.size(); i++){
            subListIndex = messages.get(i).size();
            messages.set(i, messages.get(i).subList(subListIndex-sizeMessage, subListIndex));
        }
        return messages;
    }

    public int calculateSizeMessage(List<List<String>> messages) {
        List<String> listWords = new ArrayList<String>();
        for( List<String> msg : messages){
            listWords = Stream.concat(listWords.stream(), msg.stream())
                    .distinct()
                    .collect(Collectors.toList());
        }
        listWords.remove("");
        return listWords.size();
    }

    public String proccesWordNodes(List<WordNode> wordNodes) throws MessageException {
        List<WordNode> messageWordNodes = new ArrayList<>();
        for (int i = 0; i < wordNodes.size(); i++) {
            WordNode wordNodePivot = wordNodes.get(i);
            List <WordNode> group =  groupWorkNodes(wordNodePivot, wordNodes);
            WordNode wordNodeValidate = validateWorkNode(wordNodePivot, group);
            if (wordNodeValidate == null){
                throw new MessageException("Message not descipher");
            }
            if(isExistWordNode(wordNodeValidate, messageWordNodes) == null){
                messageWordNodes.add(wordNodeValidate);
            }
        }
        String message = completeMessage(messageWordNodes);
        return message;
    }

    public List<WordNode> groupWorkNodes(WordNode wordNodePivot, List<WordNode> wordNodes){
        List<WordNode> group = new ArrayList<>();
        for (int i = 0; i < wordNodes.size(); i++) {
            if (wordNodePivot.getIndex() == wordNodes.get(i).getIndex()){
                group.add(wordNodes.get(i));
            }
        }
        return group;
    }

    public WordNode validateWorkNode(WordNode wordNodePivot, List<WordNode> wordNodes){
        boolean isValid = false;
        for (int i = 0; i < wordNodes.size(); i++) {
                if (!wordNodes.get(i).getWord().equalsIgnoreCase(wordNodePivot.getWord()) &&
                        !wordNodes.get(i).getWord().isEmpty() &&
                        !wordNodePivot.getWord().isEmpty()){
                    return null;
                }
                if (!wordNodes.get(i).getWord().isEmpty()) {
                    wordNodePivot = wordNodes.get(i);
                }

        }
        return wordNodePivot;
    }

    public WordNode isExistWordNode(WordNode wordNodePivot, List<WordNode> wordNodes) {
        for (int i = 0; i < wordNodes.size(); i++) {
            if (wordNodePivot.equals(wordNodes.get(i))){
                return wordNodePivot;
            }
        }
        return null;
    }

    public String completeMessage(List<WordNode> wordNodes) {
        String message= "";
        for (int i = 0; i < wordNodes.size(); i++) {
            message += wordNodes.get(i).getWord() + " ";
        }
        return message.substring(0, message.length()-1);
    }

}
